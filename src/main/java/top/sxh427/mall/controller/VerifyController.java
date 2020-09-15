package top.sxh427.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/login/{phone}/{type}")
    public Response login(@PathVariable("phone") String phone, @PathVariable("type") Integer type) {
        int res;
        if (type == 0) {
            res = verifyService.selectManagerByPhone(phone);
        } else {
            res = verifyService.selectUserByPhone(phone);
        }
        return res > 0 ? new Response(200, "登陆成功", null)
                       : new Response(444, "账号未注册或类型选择错误", null);
    }

    @GetMapping("/verify/{killId}/{phone}/{message}")
    public Response verify(@PathVariable("killId") Integer killId,
                           @PathVariable("phone") String phone,
                           @PathVariable("message") String message) {
         String res = "";
         if ((res = (String) redisTemplate.opsForValue().get(killId + phone + message)) != null) {
             return res.equals("success") ? new Response(200,  "请求成功！", null) :
                                            new Response(445, "后台服务器瘫痪，请联系客服！", null);
         } else {
             return new Response(444,  "排队中", null);
         }
    }
}
