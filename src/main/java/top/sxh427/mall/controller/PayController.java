package top.sxh427.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxh427.mall.entities.Response;
import top.sxh427.mall.service.OrderInfoService;

import javax.annotation.Resource;

@RestController
@RequestMapping("pay")
public class PayController {

    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/ok/{orderId}")
    public Response confirmPay(@PathVariable("orderId") Integer orderId){

        return null;
    }

}
