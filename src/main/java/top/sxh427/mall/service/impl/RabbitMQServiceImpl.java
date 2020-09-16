package top.sxh427.mall.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.service.GoodsInfoService;
import top.sxh427.mall.service.OrderInfoService;
import top.sxh427.mall.service.RabbitMQService;
import top.sxh427.mall.utils.RedisUtil;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RabbitMQServiceImpl implements RabbitMQService {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private RedisUtil redisUtil;

    @RabbitListener(queues = "orderQueue1")
    @RabbitHandler
    public void process(OrderInfo orderInfo) {
        try {
            log.info("正在处理的订单：" + orderInfo.toString());
            orderInfoService.insertOne(orderInfo);
            redisUtil.set(orderInfo.getKillId() + orderInfo.getPhone() + "wait", "success", 20, TimeUnit.MINUTES);
        } catch (Exception e) {
            redisUtil.set(orderInfo.getKillId() + orderInfo.getPhone() + "wait", "fail", 20, TimeUnit.MINUTES);
            log.error("数据插入异常");
        }
    }
    @RabbitListener(queues = "orderQueue2")
    @RabbitHandler
    public void process1(OrderInfo orderInfo) {
        try {
            log.info("正在处理的支付请求：" + orderInfo.toString());
            orderInfoService.updatePayStatusById(orderInfo.getKillId(), orderInfo.getPhone());
            goodsInfoService.updateGoodsNumsById(orderInfo.getKillId());
            redisUtil.set(orderInfo.getKillId() + orderInfo.getPhone() + "pay", "success", 20, TimeUnit.MINUTES);
        } catch (Exception e) {
            redisUtil.set(orderInfo.getKillId() + orderInfo.getPhone() + "pay", "fail", 20, TimeUnit.MINUTES);
            log.error("数据更新异常");
        }
    }

    @RabbitListener(queues = "orderQueue3")
    @RabbitHandler
    public void process2(OrderInfo orderInfo) {
        try {
            log.info("正在处理的取消请求：" + orderInfo.toString());
            orderInfoService.updatePayStatusById(orderInfo.getKillId(), orderInfo.getPhone());
            redisUtil.set(orderInfo.getKillId() + orderInfo.getPhone() + "pay", "success", 20, TimeUnit.MINUTES);
        } catch (Exception e) {
            redisUtil.set(orderInfo.getKillId() + orderInfo.getPhone() + "pay", "fail", 20, TimeUnit.MINUTES);
            log.error("数据更新异常");
        }
    }
}