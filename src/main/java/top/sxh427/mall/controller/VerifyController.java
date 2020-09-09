package top.sxh427.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxh427.mall.entities.Response;
import top.sxh427.mall.service.VerifyService;

import javax.annotation.Resource;

@RestController
@RequestMapping("kill")
public class VerifyController {

    @Resource
    private VerifyService verifyService;

    @GetMapping("/login/{phone}/{type}")
    public Response verify(@PathVariable("phone") String phone, @PathVariable("type") Integer type) {
        return null;
    }
}
