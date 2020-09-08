package top.sxh427.mall.service.impl;

import org.springframework.stereotype.Service;
import top.sxh427.mall.dao.BlackListDao;
import top.sxh427.mall.entities.BlackList;
import top.sxh427.mall.service.BlackListService;

import javax.annotation.Resource;

@Service("BlackListService")
public class BlackListServiceImpl implements BlackListService {
    @Resource
    private BlackListDao blackListDao;

    @Override
    public BlackList queryById(Integer id) {
        return blackListDao.queryById(id);
    }
}
