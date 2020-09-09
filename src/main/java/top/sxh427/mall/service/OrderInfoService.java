package top.sxh427.mall.service;

import top.sxh427.mall.entities.OrderInfo;

import java.util.List;

public interface OrderInfoService {
    /**
     * 插入一条订单信息到数据库
     * @param orderInfo 订单信息
     * @return
     */
    int insertOne(OrderInfo orderInfo);

    /**
     * 查询某条订单
     * @param orderId 订单id
     * @return
     */
    OrderInfo selectById(Integer orderId);

    /**
     * 查询所有订单信息
     * @return
     */
    List<OrderInfo> selectAll();

    /**
     * 修改支付状态为1
     * @param orderId 订单id
     * @return
     */
    int updatePayStatusById(Integer orderId);
    /**
     * 取消支付后修改订单状态为0
     * @param orderId
     * @return
     */
    int updateStatusById(Integer orderId);
}
