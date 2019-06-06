package com.sofb.redis;

import com.sofb.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;

public class RedisTemplateTest extends BaseTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void test_set() {
        //redisTemplate.opsForValue().set("yves", "11111111");
        jedisPool.getResource().set("yuji", "11111");

        System.out.println("success : " + true);
    }

    @Test
    public void test_get() {
        //String value = (String) redisTemplate.opsForValue().get("yuji");
        String value = jedisPool.getResource().get("yuji");

        System.out.println("value : " + value);
    }
}
