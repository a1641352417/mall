package top.sxh427.mall.service.impl;

import org.springframework.stereotype.Service;
import top.sxh427.mall.dao.VerifyDao;
import top.sxh427.mall.service.VerifyService;

import javax.annotation.Resource;

@Service("VerifyService")
public class VerifyServiceImpl implements VerifyService {

    @Resource
    private VerifyDao verifyDao;

    @Override
    public int selectManagerByPhone(String phone) {
        return verifyDao.selectManagerByPhone(phone);
    }

    @Override
    public int selectUserByPhone(String phone) {
        return verifyDao.selectUserByPhone(phone);
    }
}
