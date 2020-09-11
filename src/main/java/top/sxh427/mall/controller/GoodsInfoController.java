package top.sxh427.mall.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.*;
import top.sxh427.mall.entities.GoodsInfo;
import top.sxh427.mall.entities.Response;
import top.sxh427.mall.service.GoodsInfoService;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("kill")
public class GoodsInfoController {

    @Resource
    private GoodsInfoService goodsInfoService;

    @GetMapping("/get/goodsInfo/{startTime}/{endTime}")
    public List<GoodsInfo> getGoodsInfo(@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
        return goodsInfoService.selectByTime(startTime, endTime);
    }

    @PostMapping("/add/goodsInfo")
    public Response addGoodsInfo(String goodsInfo) {
        Gson gson = new Gson();
        System.out.println(goodsInfo);
        GoodsInfo goodsInfos = gson.fromJson(goodsInfo,new TypeToken<GoodsInfo>() {}.getType());
        int res = goodsInfoService.insertOne(goodsInfos);

        return res > 0 ? new Response(200, "添加成功", null)
                       : new Response(444, "添加失败", null);
    }
}
