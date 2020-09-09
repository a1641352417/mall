package top.sxh427.mall.service.impl;

import org.springframework.stereotype.Service;
import top.sxh427.mall.dao.OrderInfoDao;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.service.OrderInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("OrderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoDao orderInfoDao;

    @Override
    public int insertOne(OrderInfo orderInfo) {
        return orderInfoDao.insertOne(orderInfo);
    }

    @Override
    public OrderInfo selectById(Integer orderId) {
        return orderInfoDao.selectById(orderId);
    }

    @Override
    public List<OrderInfo> selectAll() {
        return orderInfoDao.selectAll();
    }

    @Override
    public int updatePayStatusById(Integer orderId) {
        return orderInfoDao.updatePayStatusById(orderId);
    }

    @Override
    public int updateStatusById(Integer orderId) {
        return orderInfoDao.updateStatusById(orderId);
    }
}
