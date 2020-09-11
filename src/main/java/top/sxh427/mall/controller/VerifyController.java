package top.sxh427.mall.controller;

import org.springframework.web.bind.annotation.*;
import top.sxh427.mall.entities.Response;
import top.sxh427.mall.service.VerifyService;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("kill")
public class VerifyController {

    @Resource
    private VerifyService verifyService;

    @PostMapping("/login/{phone}/{type}")
    public Response verify(@PathVariable("phone") String phone, @PathVariable("type") Integer type) {
        int res;
        if (type == 0) {
            res = verifyService.selectManagerByPhone(phone);
        } else {
            res = verifyService.selectUserByPhone(phone);
        }
        return res > 0 ? new Response(200, "登陆成功", null)
                       : new Response(444, "账号未注册或类型选择错误", null);
    }
}
