package top.sxh427.mall.controller;

import org.springframework.web.bind.annotation.*;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.entities.Response;
import top.sxh427.mall.service.OrderInfoService;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("kill")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/get/record")
    public List<OrderInfo> getAllOrderInfo(){
        return orderInfoService.selectAll();
    }

    @GetMapping("/buy/{phone}/{killId}")
    public Response buy(@PathVariable("phone") String phone, @PathVariable("killId") Integer killId) {

        return null;
    }
}
