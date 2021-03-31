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

}
