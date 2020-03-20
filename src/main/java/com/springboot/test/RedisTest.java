package com.springboot.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        new StringRedisTemplate().afterPropertiesSet();
        ValueOperations<String, String> stringStringValueOperations = new StringRedisTemplate().opsForValue();
        stringStringValueOperations.set("zhangsan","nan");
        stringStringValueOperations.set("lisi","nan");
        stringStringValueOperations.set("wangwu","nv");
        System.out.println(stringStringValueOperations.get("zhangsan"));
        System.out.println(stringStringValueOperations.get("lisi"));
        System.out.println(stringStringValueOperations.get("wangwu"));
    }


}
