package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/3/31
 */
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("add")
    public String addTest(){
        redisTemplate.opsForValue().set("name","hesuijin");
        return "add success";
    }

    @GetMapping("get")
    public String getTest(){
       String value =  redisTemplate.opsForValue().get("name");
        return value;
    }
}
