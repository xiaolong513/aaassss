package com.sofb.cache;

import com.sofb.enums.CacheType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component("cache")
public class RedisCache {

    @Resource(name = "redisClusterTemplate")
    private RedisTemplate<String, Object> redisClusterTemplate;

    @Resource(name = "stringClusterTemplate")
    private RedisTemplate<String, Object> stringClusterTemplate;

    public Object get(String key) {
        return redisClusterTemplate.opsForValue().get(key);
    }

    public void put(String key, Object value, long timeout) {
        redisClusterTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void put(String key, Object value) {
        redisClusterTemplate.opsForValue().set(key, value);
    }

    public void update(String key, Object value) {
        redisClusterTemplate.opsForValue().set(key, value);
    }

    public void remove(String key) {
        redisClusterTemplate.delete(key);
    }

    public void clear() {
        return;
    }


    /**
     * *
     * 返回redis模板对象
     *
     * @param cacheType
     * @return
     */
    public RedisTemplate getRedisTemplate(CacheType cacheType) {
        if (cacheType == CacheType.SESSION) {
            return stringClusterTemplate;
        } else if (cacheType == CacheType.SAVE) {
            return redisClusterTemplate;
        } else {
            return redisClusterTemplate;
        }
    }


    /**
     * 获取自增结果的方法
     *
     * @param key
     * @param redisTemplate
     * @return
     */
    public long getIncrValue(final String key, RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] rowkey = serializer.serialize(key);
            byte[] rowval = connection.get(rowkey);
            try {
                String val = serializer.deserialize(rowval);
                return Long.parseLong(val);
            } catch (Exception e) {
                return 0L;
            }
        });
    }
}
