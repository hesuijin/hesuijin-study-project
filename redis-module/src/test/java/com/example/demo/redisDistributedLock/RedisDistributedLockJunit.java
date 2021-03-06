package com.example.demo.redisDistributedLock;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 分布式锁测试
 * @Author HeSuiJin
 * @Date 2021/4/12
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisDistributedLockJunit {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void redisLockTest() {

        RedisConnection connection = null;
        connection = redisTemplate.getConnectionFactory().getConnection();

        String key = "member:name:HSJ";
        byte[] content ="content".getBytes();

        //关键点1：如果该key已经存在 则返回false  同时不会修改其内容
       boolean isExist =  connection.setNX(key.getBytes(), content);
       System.out.println("是否已经存在:"+isExist);

        //注意保证使用的key是存在的
        //redisTemplate.expire(key,timeout,timeunit);
        //参数说明  key 需要设置的key  timeout：key的生存时间  timeuint：时间单位（小时，分钟，秒……）
        //TimeUnit.MILLISECONDS 是毫秒

        // 关键点2：重置过期时间
        redisTemplate.expire(key, 15000L, TimeUnit.MILLISECONDS);

        //获取过期时间
        System.out.println("获取过期时间:"+redisTemplate.getExpire(key));
    }
}
