package top.sxh427.mall.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {
    private Integer killId; //秒杀id，自增
    private String goodsName; //商品名称
    private Integer goodsNums; //商品库存数量
    private Double price; //商品价格
    private String image; //商品图片
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS", timezone = "GMT+8")
    private Date startTime; //活动开始时间
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS", timezone = "GMT+8")
    private Date endTime; //活动结束时间
    private Integer status; //0代表无效，1代表有效
}
