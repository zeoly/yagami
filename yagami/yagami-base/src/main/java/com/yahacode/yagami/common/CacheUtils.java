package com.yahacode.yagami.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zengyongli 2018-03-05
 */
public class CacheUtils {

    @Autowired
    private static RedisTemplate redisTemplate;

    public static boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
