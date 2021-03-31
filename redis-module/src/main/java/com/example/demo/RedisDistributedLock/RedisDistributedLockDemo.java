package com.example.demo.RedisDistributedLock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/3/31
 */
@Slf4j
public class RedisDistributedLockDemo {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //注意保证使用的key是存在的
    //redisTemplate.expire(key,timeout,timeunit);
    //参数说明  key 需要设置的key  timeout：key的生存时间  timeuint：时间单位（小时，分钟，秒……）

    //TimeUnit.MILLISECONDS 是毫秒

    public void expireTest(){
        redisTemplate.expire("我的名字",1000L, TimeUnit.MILLISECONDS);
        log.info("setTest 请求返回：{}","success");
    }
}
