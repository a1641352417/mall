package top.sxh427.mall.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    private Integer orderId; //订单id，自增
    private Integer killId; //秒杀id
    private String phone; //手机号
    private String payStatus; //待支付或已支付
    private Integer status; //0代表无效，1代表有效
}
