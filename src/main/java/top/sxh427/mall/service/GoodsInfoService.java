package top.sxh427.mall.service;

import top.sxh427.mall.entities.GoodsInfo;

import java.util.Date;
import java.util.List;

public interface GoodsInfoService {
    /**
     * 插入一条秒杀活动的商品信息
     * @param goodsInfo 商品信息
     * @return
     */
    int insertOne(GoodsInfo goodsInfo);

    /**
     * 返回某个时间段的所有商品信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<GoodsInfo> selectByTime(Date startTime, Date endTime);

    /**
     * 更新商品库存
     * @param killId 秒杀id
     * @return
     */
    int updateGoodsNumsById(Integer killId);
}
