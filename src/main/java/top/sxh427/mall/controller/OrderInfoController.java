package top.sxh427.mall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.entities.Response;
import top.sxh427.mall.service.OrderInfoService;
import top.sxh427.mall.utils.RedisUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("kill")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AmqpTemplate amqpTemplate;

    @GetMapping("/get/record")
    public List<OrderInfo> getAllOrderInfo(){
        List<OrderInfo> orderInfos = orderInfoService.selectAll();
        if (orderInfos.size() > 0) {
            orderInfos.get(0).setStatus(8585);
        }
        return orderInfos;
    }

    @GetMapping("/buy/{killId}/{phone}")
    public Response buy(@PathVariable("killId") Integer killId, @PathVariable( "phone") String phone) {
        if(redisTemplate.opsForValue().get("run") != null //活动开始
        && redisTemplate.opsForValue().get(killId.toString()) != null) { //商品在redis中,防止别人直接使用该链接访问
            if(redisTemplate.opsForValue().decrement(killId.toString()) >= 0) {
                OrderInfo orderInfo = new OrderInfo(killId, phone, "待支付", 1);
                this.amqpTemplate.convertAndSend("orderQueue1",orderInfo); //发送到队列
                redisUtil.set(killId + phone, "1", 2, TimeUnit.HOURS); //设置一个全局Id，解决接口幂等性
                return new Response(200, "抢购成功，请支付！", orderInfo);
            } else {
                redisUtil.delete("run"); //标志活动结束
                return new Response(444, "该商品已被抢购完！", null);
            }
        } else {
            return new Response(444, "活动未开始或已结束！", null);
        }
    }
}
