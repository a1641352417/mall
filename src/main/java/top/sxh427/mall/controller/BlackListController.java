package top.sxh427.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxh427.mall.entities.BlackList;
import top.sxh427.mall.service.BlackListService;

import javax.annotation.Resource;


@RestController
@RequestMapping("kill")
public class BlackListController {
    @Resource
    private BlackListService blackListService;

    @GetMapping("query")
    public BlackList query(Integer id) {
        return blackListService.queryById(id);
    }
}
