package top.sxh427.mall.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.service.OrderInfoService;
import top.sxh427.mall.service.RabbitMQService;

import javax.annotation.Resource;

@Slf4j
@Service
public class RabbitMQServiceImpl implements RabbitMQService {

    @Resource
    private OrderInfoService orderInfoService;

    @RabbitListener(queues = "orderQueue1")
    @RabbitHandler
    public void process(OrderInfo orderInfo) {
        try {
            log.info("正在处理的订单：" + orderInfo.toString());
            orderInfoService.insertOne(orderInfo);
        } catch (Exception e) {
        }
    }
    @RabbitListener(queues = "orderQueue2")
    @RabbitHandler
    public void process1(OrderInfo orderInfo) {
        try {
            log.info("正在处理的支付请求：" + orderInfo.toString());
            orderInfoService.updatePayStatusById(orderInfo.getKillId(), orderInfo.getPhone());
        } catch (Exception e) {
        }
    }

    @RabbitListener(queues = "orderQueue3")
    @RabbitHandler
    public void process2(OrderInfo orderInfo) {
        try {
            log.info("正在处理的取消请求：" + orderInfo.toString());
            orderInfoService.updatePayStatusById(orderInfo.getKillId(), orderInfo.getPhone());
        } catch (Exception e) {
        }
    }
}