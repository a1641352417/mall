package top.sxh427.mall.service;

import top.sxh427.mall.entities.OrderInfo;

public interface RabbitMQService {
    /**
     * 处理订单生成请求
     * @param orderInfo
     */
    void process(OrderInfo orderInfo);

    /**
     * 处理支付请求
     * @param orderInfo
     */
    void process1(OrderInfo orderInfo);

    /**
     * 处理取消支付请求
     * @param orderInfo
     */
    void process2(OrderInfo orderInfo);
}
