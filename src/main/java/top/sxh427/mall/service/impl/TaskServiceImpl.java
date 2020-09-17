package top.sxh427.mall.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {


    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @Scheduled(cron = "0 0 10,14,16 * * ? ")
    public void startRun() {
        log.info("start");
        redisUtil.set("run", "run", 602, TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 55 9,13,15 * * ? ")
    public void load() {
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        startTime = startTime.substring(0, 13) + ":55:00";
        String endTime = startTime.substring(0, 12) + (char)(startTime.charAt(12) + 1) + ":15:00";
//        startTime = startTime.substring(0, 13) + ":00:00";
//        String endTime = "2020-09-16 10:15:00";
        log.info(startTime);
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
