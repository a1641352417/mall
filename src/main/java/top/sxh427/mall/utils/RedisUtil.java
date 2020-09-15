package top.sxh427.mall.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis操作工具类.
 */
@Component
public class RedisUtil {
    private final RedisTemplate<String, String> redisTemplate;

    public RedisUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, String value, long timeout, TimeUnit unit) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = redisTemplate.expire(key, timeout, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            result = redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
