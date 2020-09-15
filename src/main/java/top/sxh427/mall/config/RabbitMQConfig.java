package top.sxh427.mall.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * 该队列用于处理订单生成请求
     * @return
     */
    @Bean
    public Queue orderQueue1() {
        return new Queue("orderQueue1", true);
    }

    /**
     * 该队列用于处理支付请求
     * @return
     */
    @Bean
    public Queue orderQueue2() {
        return new Queue("orderQueue2", true);
    }

    /**
     * 该队列用于处理取消请求
     * @return
     */
    @Bean
    public Queue orderQueue3() {
        return new Queue("orderQueue3", true);
    }

}
