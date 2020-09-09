package top.sxh427.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxh427.mall.entities.GoodsInfo;
import top.sxh427.mall.service.GoodsInfoService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("kill")
public class GoodsInfoController {

    @Resource
    private GoodsInfoService goodsInfoService;

    @GetMapping("/get/goodsInfo/{startTime}/{endTime}")
    public List<GoodsInfo> getGoodsInfo(@PathVariable("startTime") Date startTime, @PathVariable("endTime") Date endTime) {
        return goodsInfoService.selectByTime(startTime, endTime);
    }
}
