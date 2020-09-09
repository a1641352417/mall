package top.sxh427.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.sxh427.mall.entities.GoodsInfo;

import java.util.Date;
import java.util.List;

@Mapper
public interface GoodsInfoDao {
    /**
     * 插入一条秒杀活动的商品信息
     * @param goodsInfo 商品信息
     * @return
     */
    int insertOne(@Param("goodsInfo") GoodsInfo goodsInfo);

    /**
     * 返回某个时间段的所有商品信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<GoodsInfo> selectByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 查询商品库存
     * @param killId
     * @return
     */
    int selectGoodsNumsById(@Param("killId") Integer killId);

    /**
     * 更新商品库存
     * @param killId 秒杀id
     * @return
     */
    int updateGoodsNumsById(@Param("killId") Integer killId);

}
