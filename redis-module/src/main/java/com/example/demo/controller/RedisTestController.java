package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/3/31
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisTestController {

    //在这里面以及注入了属性
    @Autowired
    private StringRedisTemplate redisTemplate;


    @GetMapping("set")
    public String addTest() {
        //set  key value
        redisTemplate.opsForValue().set("name", "hesuijin");
        return "set success";
    }

    @GetMapping("get")
    public String getTest() {
        //get  key value
        String value = redisTemplate.opsForValue().get("name");
        return value;
    }

    @GetMapping("putHash")
    public String setHash() {
        //put  redisKey hashKey  hashValue
        redisTemplate.opsForHash().put("redisKey", "hashKey0", "hashValue0");
        redisTemplate.opsForHash().put("redisKey", "hashKey1", "hashValue1");
        redisTemplate.opsForHash().put("redisKey", "hashKey2", "hashValue2");
        return "put success";
    }

    @GetMapping("getHash")
    public String getHash() {
        //原生方法 对应于boundHashOperations.keys()
        log.info("获取该redisKey 所有对应的value"+redisTemplate.opsForHash().keys("redisKey"));
        //原生方法 对应于boundHashOperations.values()
        log.info("获取该redisKey 所有对应的value"+redisTemplate.opsForHash().values("redisKey"));
        //原生方法 对应于boundHashOperations.entries()
        log.info("获取该redisKey 所有对应的key value"+redisTemplate.opsForHash().entries("redisKey"));

        //get  redisKey hashKey
        Object hashValue = redisTemplate.opsForHash().get("redisKey", "hashKey0");
        return hashValue == null ? "该redisKey对应的hashKey为null" : hashValue.toString();
    }

    @GetMapping("delete")
    public String deleteKey(String redisKey) {
       Boolean flag = redisTemplate.delete(redisKey);
        return "是否删除成功:"+flag;
    }
}
