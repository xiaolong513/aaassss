package com.sofb.redis;

import com.sofb.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTemplateTest extends BaseTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test_set() {
        redisTemplate.opsForValue().set("yves", "11111111");

        System.out.println("success : " + true);
    }

    @Test
    public void test_get() {
        String value = (String) redisTemplate.opsForValue().get("yves");

        System.out.println("value : " + value);
    }
}
