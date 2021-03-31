package com.example.demo.redisString;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/3/31
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisStringJunit {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void setTest(){
         redisTemplate.opsForValue().set("name","hesuijin");
        log.info("setTest 请求返回：{}","success");
    }

    @Test
    public void getTest(){
        String value = redisTemplate.opsForValue().get("name");
        log.info("getTest 请求返回：{}",JSONObject.toJSONString(value));
    }

    /**
     * 批量插入
     */
    @Test
    public void mulSetTest(){
        Map map = new HashMap();
        map.put("我的名字1","hesuijin");
        map.put("我的名字2","HSJ");

        redisTemplate.opsForValue().multiSet(map);
        log.info("mulSetTest 请求返回：{}","success");
    }

    /**
     * 批量返回
     */
    @Test
    public void mulGetTest(){
        List<String> stringList = new ArrayList<>();
        stringList.add("我的名字1");
        stringList.add("我的名字2");

        List<String> returnString = redisTemplate.opsForValue().multiGet(stringList);
        log.info("mulGetTest 请求返回：{}", JSONObject.toJSONString(returnString));
    }

    @Test
    public void appendTest(){
        //如果原信息存在就追加 否则新增
        redisTemplate.opsForValue().append("name","我我我");
        log.info("appendTest 请求返回：{}","success");

        String value = redisTemplate.opsForValue().get("name");
        log.info("getTest 请求返回：{}",JSONObject.toJSONString(value));
    }

    /**
     * 修改前获取之前的值
     */
    @Test
    public void getAndSetTest(){
        String returnString  =redisTemplate.opsForValue().getAndSet("MyName","你好啊");
        log.info("getAndSet 请求返回：{}",returnString);
    }

    @Test
    public void setIfAbsent(){
        //如果不存在 则新增 并且设置过期时间 为10秒
        redisTemplate.opsForValue().setIfAbsent("我存在10秒","我经过了setIfAbsent新增",10000L,TimeUnit.MILLISECONDS);
        log.info("setIfAbsent 请求返回：{}","success");
    }

    @Test
    public void setIfAbsentDuration(){
        //如果不存在 则新增 并且设置过期时间 为10秒
        Duration duration = Duration.ofSeconds(10);
        redisTemplate.opsForValue().setIfAbsent("我存在10秒","我经过了setIfAbsent新增",duration);
        log.info("setIfAbsent 请求返回：{}","success");
    }

    @Test
    public void setIfPresent(){
        //如果存在 则修改 并且设置过期时间 为10秒
        redisTemplate.opsForValue().setIfPresent("我存在10秒","我经过了setIfPresent修改",10000L,TimeUnit.MILLISECONDS);
        log.info("setIfPresent 请求返回：{}","success");
    }


    @Test
    public void setIfPresentDuration(){
        //如果存在 则修改 并且设置过期时间 为10秒
        Duration duration = Duration.ofSeconds(10);
        redisTemplate.opsForValue().setIfPresent("我存在10秒","我经过了setIfPresent修改",duration);
        log.info("setIfPresent 请求返回：{}","success");
    }

    @Test
    public void incLong(){
        //如果存在则加1  如果不存在则初始值为1
        Long returnNumber= redisTemplate.opsForValue().increment("111");

        //如果存在则加10  如果不存在则初始值为10
//        Long returnNumber= redisTemplate.opsForValue().increment("111",10);
        log.info("incLong 请求返回：{}",returnNumber);
    }

    @Test
    public void decLong(){
        //如果存在则减少1  如果不存在则初始值为-1
        Long returnNumber= redisTemplate.opsForValue().decrement("999");

        //如果存在则减10  如果不存在则初始值为-10
//        Long returnNumber= redisTemplate.opsForValue().increment("111",10);
        log.info("decLong 请求返回：{}",returnNumber);
    }


    @Test
    public void incDouble(){
        //如果存在则加9.9  如果不存在则初始值为 9.9
        double returnNumber= redisTemplate.opsForValue().increment("111",9.9);
        log.info("incDouble 请求返回：{}",returnNumber);
    }

    @Test
    public void decDouble(){
        //如果存在则减9.9  如果不存在则初始值为 -9.9
        double returnNumber= redisTemplate.opsForValue().increment("111",9.9);
        log.info("decDouble 请求返回：{}",returnNumber);
    }
}
