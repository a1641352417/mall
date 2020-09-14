package top.sxh427.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.sxh427.mall.entities.GoodsInfo;
import top.sxh427.mall.service.GoodsInfoService;
import top.sxh427.mall.service.TaskService;
import top.sxh427.mall.utils.RedisUtil;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @Scheduled(cron = "0 0 14,16 * * ? ")
    public void startRun() {
        redisUtil.set("run", "run", 602, TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 55 13,15 * * ? ")
    public void load() {
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        startTime = startTime.substring(0, 13) + ":55:00";
        String endTime = startTime.substring(0, 12) + (char)(startTime.charAt(12) + 1) + ":15:00";
        List<GoodsInfo> goodsInfos = goodsInfoService.selectByTime(startTime, endTime);
        if (goodsInfos.size() != 0) {
            for (int i = 0; i < goodsInfos.size(); i++) {
                redisUtil.set(goodsInfos.get(i).getKillId().toString(),
                        goodsInfos.get(i).getGoodsNums().toString(),
                        20, TimeUnit.MINUTES);
            }
        }

    }



}
