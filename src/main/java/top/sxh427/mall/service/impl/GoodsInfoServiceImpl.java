package top.sxh427.mall.service.impl;

import org.springframework.stereotype.Service;
import top.sxh427.mall.dao.GoodsInfoDao;
import top.sxh427.mall.entities.GoodsInfo;
import top.sxh427.mall.service.GoodsInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service("GoodsInfoService")
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    private GoodsInfoDao goodsInfoDao;

    @Override
    public int insertOne(GoodsInfo goodsInfo) {
        return goodsInfoDao.insertOne(goodsInfo);
    }

    @Override
    public List<GoodsInfo> selectByTime(String startTime, String endTime) {
        return goodsInfoDao.selectByTime(startTime, endTime);
    }

    @Override
    public int updateGoodsNumsById(Integer killId) {
        return goodsInfoDao.updateGoodsNumsById(killId);
    }
}
