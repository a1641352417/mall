package top.sxh427.mall;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import top.sxh427.mall.entities.GoodsInfo;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.service.GoodsInfoService;
import top.sxh427.mall.service.OrderInfoService;
import top.sxh427.mall.service.VerifyService;
import top.sxh427.mall.utils.RedisUtil;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class MallApplicationTests {

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private VerifyService verifyService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Resource
    RedisUtil redisUtil;

    @Resource
    private AmqpTemplate amqpTemplate;

    void test() {
        orderInfoService.updatePayStatusById(11, "11111111112");
        goodsInfoService.updateGoodsNumsById(11);
    }

    void time() throws InterruptedException {
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        startTime = startTime.substring(0, 13) + ":55:00";
        String endTime = startTime.substring(0, 12) + (char)(startTime.charAt(12) + 1) + ":15:00";
        System.out.println(startTime);
        System.out.println(endTime);
        redisUtil.set("123", "50", 5, TimeUnit.SECONDS);
        Thread.sleep(6000);
        Long decrement = redisTemplate.opsForValue().decrement("123");
        System.out.println(redisTemplate.opsForValue().get("123") + ",decrement:" + decrement);
    }

    void redisTest() throws InterruptedException {
        redisUtil.set("123", "50", 5, TimeUnit.SECONDS);
        Thread.sleep(6000);
        System.out.println("getFail1:" + redisTemplate.opsForValue().get("123"));
        System.out.println("delete fail:" + redisUtil.delete("123"));
        redisUtil.set("123", "50", 5, TimeUnit.SECONDS);
        System.out.println("getFail2:" + redisTemplate.opsForValue().get("123"));
        System.out.println("delete fail:" + redisUtil.delete("123"));
        boolean set = redisUtil.set("5", "50", 5, TimeUnit.MINUTES);
        System.out.println(redisTemplate.opsForValue().get("5") + ",set:" + set);
        Long decrement = redisTemplate.opsForValue().decrement("5");
        System.out.println(redisTemplate.opsForValue().get("5") + ",decrement:" + decrement);
        Long increment = redisTemplate.opsForValue().increment("5");
        System.out.println(redisTemplate.opsForValue().get("5") + ",increment:" + increment);
        System.out.println("getFail:" + redisTemplate.opsForValue().get("4"));
        System.out.println("delete success:" + redisUtil.delete("5"));
        System.out.println("delete fail:" + redisUtil.delete("5"));
    }

    void contextLoads() throws ParseException {
        int i = goodsInfoService.insertOne(new GoodsInfo(0, "亨得利眼镜", 20, 1.00, "localhost:2323/a.jpg",
                "2020-09-10 10:00:00",
                "2020-09-10 10:10:00", 1));
        System.out.println("goodsInfoService.insertOne:" + i);
        int j = goodsInfoService.updateGoodsNumsById(7);
        System.out.println("goodsInfoService.updateGoodsNumsById:" + j);
        List<GoodsInfo> goodsInfos = goodsInfoService.selectByTime("2020-09-10 14:00:00", "2020-09-10 14:10:00");
        goodsInfos.forEach(System.out :: println);
    }


    void order() {
        int k = orderInfoService.insertOne(new OrderInfo(7, "11111111111", "待支付", 1));
        int o = orderInfoService.insertOne(new OrderInfo(7, "11111111112", "待支付", 1));
        System.out.println("orderInfoService.insertOne:" + k + "," + o);
        OrderInfo orderInfo = orderInfoService.selectById(7, "11111111111");
        System.out.println(orderInfo);
        int q = orderInfoService.updatePayStatusById(7, "11111111111");
        System.out.println("orderInfoService.updatePayStatusById:" + q);
        List<OrderInfo> orderInfos = orderInfoService.selectAll();
        orderInfos.forEach(System.out :: println);
        int w = orderInfoService.updateStatusById(7, "11111111111");
        System.out.println("orderInfoService.updateStatusById:" + w);
    }


    void verify() {
        int r = verifyService.selectManagerByPhone("18854642134");
        int k = verifyService.selectManagerByPhone("188546134");
        System.out.println("verifyService.selectManagerByPhone:" + r + "," + k);

        int t = verifyService.selectUserByPhone("11111111111");
        int q = verifyService.selectUserByPhone("1111111111");
        System.out.println("verifyService.selectUserByPhone:" + t + "," + q);
    }


    void api(){
        GoodsInfo goodsInfo = new GoodsInfo(null, "iPhone 11", 2, 1.00, "E:/image/iPhone 11.jpg", "2020-09-10 14:00:00", "2020-09-10 14:10:00", null);
        Gson gson = new Gson();
        String res = gson.toJson(goodsInfo);
        System.out.println(res);
    }


    void test1() {
        String json = "{\n" +
                "\t\"goodsName\": \"a\",\n" +
                "\t\"goodsNums\": 1,\n" +
                "\t\"price\": 1,\n" +
                "\t\"image\": \"55\",\n" +
                "\t\"startTime\": \"2020-9-15 16:00:00\",\n" +
                "\t\"endTime\": \"2020-9-15 16:00:00\"\n" +
                "}";
        Gson gson = new Gson();
        GoodsInfo goodsInfos = gson.fromJson(json,new TypeToken<GoodsInfo>() {}.getType());
        System.out.println(goodsInfos.toString());
    }


    void queue() {
        this.amqpTemplate.convertAndSend("orderQueue1", new OrderInfo(0,"123","已支付",1));
        this.amqpTemplate.convertAndSend("orderQueue2", new OrderInfo(0,"13","已支付",1));
    }
}
