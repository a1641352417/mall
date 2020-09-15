package top.sxh427.mall.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.*;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.entities.Response;
import top.sxh427.mall.service.OrderInfoService;
import top.sxh427.mall.utils.RedisUtil;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("pay")
public class PayController {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AmqpTemplate amqpTemplate;

    @GetMapping("/ok/{killId}/{phone}")
    public Response confirmPay(@PathVariable("killId") Integer killId, @PathVariable("phone") String phone){
        if (redisUtil.delete(killId + phone)) { //删除成功代表第一次请求
            amqpTemplate.convertAndSend("orderQueue2", new OrderInfo(killId, phone, null, null));
            int res = orderInfoService.updatePayStatusById(killId, phone);
            return new Response(200, "请求发送成功，请等待！", null);

        } else {
            return  new Response(444, "请不要多次点击！", null);
        }
    }

    @GetMapping("/cancel/{killId}/{phone}")
    public Response cancelPay(@PathVariable("killId") Integer killId, @PathVariable("phone") String phone){
        if (redisUtil.delete(killId + phone)) { //删除成功代表第一次请求
            amqpTemplate.convertAndSend("orderQueue3", new OrderInfo(killId, phone, null, null));
            int res = orderInfoService.updateStatusById(killId, phone);
            return new Response(200, "请求发送成功，请等待！", null);

        } else {
            return  new Response(444, "请不要多次点击！", null);
        }
    }

}
