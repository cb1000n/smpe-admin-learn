package com.zhang.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ClassName RedisUtils
 * Description TODO 类描述 SpringData Redis 的工具类
 *
 * @author ZhangRenjie
 * Date  2021/1/19 15:59
 */
@RequiredArgsConstructor
@Component
@SuppressWarnings({"unchecked","all"})
@Slf4j
public class RedisUtils {
    
    private final RedisTemplate<Object, Object> redisTemplate;
    
    /**
     * Description //TODO 存入 redis，并设置过期时间
     * @param key 键
     * @param value 值
     * @param time 过期时间
     * @param timeUnit 类型
     * @return boolean 是否存入成功
     * @author ZhangRenJie
     * @date 2021/1/20 8:13
     */
    public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Description //TODO 普通 redis 存入
     * @param key 键
     * @param value 值
     * @return boolean 是否存入成功
     * @author ZhangRenJie
     * @date 2021/1/20 8:07
     */
    private boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}