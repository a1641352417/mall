package top.sxh427.mall.service;

public interface TaskService {
    /**
     * 每天14点、16点整开启活动,活动时间持续十分钟
     */
    void startRun();


    /**
     * 每天13:55:00、15:55:00开始导入数据库的商品数据到redis中
     */
    void load();


}
