package top.sxh427.mall.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.sxh427.mall.dao.OrderInfoDao;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.service.OrderInfoService;
import top.sxh427.mall.utils.RedisUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("OrderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoDao orderInfoDao;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public int insertOne(OrderInfo orderInfo) {
        return orderInfoDao.insertOne(orderInfo);
    }

    @Override
    public OrderInfo selectById(Integer killId, String phone) {
        return orderInfoDao.selectById(killId, phone);
    }

    @Override
    public List<OrderInfo> selectAll() {
        return orderInfoDao.selectAll();
    }

    @Override
    public int updatePayStatusById(Integer killId, String phone) {
        return orderInfoDao.updatePayStatusById(killId, phone);
    }

    @Override
    public int updateStatusById(Integer killId, String phone) {
        return orderInfoDao.updateStatusById(killId, phone);
    }
}
