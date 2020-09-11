package top.sxh427.mall.controller;

import org.springframework.web.bind.annotation.*;
import top.sxh427.mall.entities.Response;
import top.sxh427.mall.service.OrderInfoService;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("pay")
public class PayController {

    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/ok/{orderId}")
    public Response confirmPay(@PathVariable("orderId") Integer orderId){
        int res = orderInfoService.updatePayStatusById(orderId);
        return res > 0 ? new Response(200, "支付成功", null)
                       : new Response(444, "支付失败", null);
    }

    @GetMapping("/cancel/{orderId}")
    public Response cancelPay(@PathVariable("orderId") Integer orderId){
        int res = orderInfoService.updateStatusById(orderId);
        return res > 0 ? new Response(200, "取消成功", null)
                : new Response(444, "取消失败", null);
    }

}
