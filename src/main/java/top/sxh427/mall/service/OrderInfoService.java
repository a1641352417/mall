package top.sxh427.mall.service;

import org.apache.ibatis.annotations.Param;
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
     * @param killId 秒杀id
     * @param phone 手机号
     * @return
     */
    OrderInfo selectById(Integer killId, String phone);

    /**
     * 查询所有订单信息
     * @return
     */
    List<OrderInfo> selectAll();

    /**
     * 修改支付状态为1
     * @param killId 秒杀id
     * @param phone 手机号
     * @return
     */
    int updatePayStatusById(Integer killId, String phone);
    /**
     * 取消支付后修改订单状态为0
     * @param killId 秒杀id
     * @param phone 手机号
     * @return
     */
    int updateStatusById(Integer killId, String phone);
}
