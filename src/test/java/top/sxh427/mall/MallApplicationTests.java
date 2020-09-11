package top.sxh427.mall;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.sxh427.mall.entities.GoodsInfo;
import top.sxh427.mall.entities.OrderInfo;
import top.sxh427.mall.service.GoodsInfoService;
import top.sxh427.mall.service.OrderInfoService;
import top.sxh427.mall.service.VerifyService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MallApplicationTests {

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private VerifyService verifyService;

    @Test
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

    @Test
    void order() {
        int k = orderInfoService.insertOne(new OrderInfo(0, 7, "11111111111", "待支付", 1));
        int o = orderInfoService.insertOne(new OrderInfo(0, 7, "11111111112", "待支付", 1));
        System.out.println("orderInfoService.insertOne:" + k + "," + o);
        OrderInfo orderInfo = orderInfoService.selectById(1);
        System.out.println(orderInfo);
        int q = orderInfoService.updatePayStatusById(1);
        System.out.println("orderInfoService.updatePayStatusById:" + q);
        List<OrderInfo> orderInfos = orderInfoService.selectAll();
        orderInfos.forEach(System.out :: println);
        int w = orderInfoService.updateStatusById(5);
        System.out.println("orderInfoService.updateStatusById:" + w);
    }

    @Test
    void verify() {
        int r = verifyService.selectManagerByPhone("18854642134");
        int k = verifyService.selectManagerByPhone("188546134");
        System.out.println("verifyService.selectManagerByPhone:" + r + "," + k);

        int t = verifyService.selectUserByPhone("11111111111");
        int q = verifyService.selectUserByPhone("1111111111");
        System.out.println("verifyService.selectUserByPhone:" + t + "," + q);
    }

    @Test
    void api(){
        GoodsInfo goodsInfo = new GoodsInfo(null, "iPhone 11", 2, 1.00, "E:/image/iPhone 11.jpg", "2020-09-10 14:00:00", "2020-09-10 14:10:00", null);
        Gson gson = new Gson();
        String res = gson.toJson(goodsInfo);
        System.out.println(res);
    }
}
