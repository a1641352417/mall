package top.sxh427.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.sxh427.mall.entities.OrderInfo;

import java.util.List;

@Mapper
public interface OrderInfoDao {

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
    OrderInfo selectById(@Param("orderId") Integer orderId);

    /**
     * 查询所有订单信息
     * @return
     */
    List<OrderInfo> selectAll();

    /**
     * 支付成功后修改支付状态为已支付
     * @param orderId 订单id
     * @return
     */
    int updatePayStatusById(@Param("orderId") Integer orderId);

    /**
     * 取消支付后修改订单状态为0
     * @param orderId
     * @return
     */
    int updateStatusById(@Param("orderId") Integer orderId);
}
