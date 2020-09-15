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
        int res = 0;
        try {
            res = orderInfoDao.insertOne(orderInfo);
            redisUtil.set(orderInfo.getKillId() + orderInfo.getPhone() + "wait", "success", 20, TimeUnit.MINUTES);
        } catch (Exception e) {
            redisUtil.set(orderInfo.getKillId() + orderInfo.getPhone() + "wait", "fail", 20, TimeUnit.MINUTES);
            log.error("数据插入异常");
        }
        return res;
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
        int res = 0;
        try {
            res = orderInfoDao.updatePayStatusById(killId, phone);
            redisUtil.set(killId + phone + "pay", "success", 20, TimeUnit.MINUTES);
        } catch (Exception e) {
            redisUtil.set(killId + phone + "pay", "fail", 20, TimeUnit.MINUTES);
            log.error("数据更新异常");
        }
        return res;
    }

    @Override
    public int updateStatusById(Integer killId, String phone) {
        int res = 0;
        try {
            res = orderInfoDao.updateStatusById(killId, phone);
            redisUtil.set(killId + phone + "pay", "success", 20, TimeUnit.MINUTES);
        } catch (Exception e) {
            redisUtil.set(killId + phone + "pay", "fail", 20, TimeUnit.MINUTES);
            log.error("数据更新异常");
        }
        return res;
    }
}
