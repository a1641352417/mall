package top.sxh427.mall.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo implements Serializable {
    private Integer killId; //秒杀id，自增
    private String goodsName; //商品名称
    private Integer goodsNums; //商品库存数量
    private Double price; //商品价格
    private String image; //商品图片
    private String startTime; //活动开始时间
    private String endTime; //活动结束时间
    private Integer status; //0代表无效，1代表有效
}
