package com.example.demo.redisString;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void getTest(){
        String value = redisTemplate.opsForValue().get("name");
        log.info(JSONObject.toJSONString(value));
    }

}
