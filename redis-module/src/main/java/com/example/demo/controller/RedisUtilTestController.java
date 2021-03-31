package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import com.example.demo.utils.RedisStringUtil;
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
@RequestMapping("/redisUtil")
public class RedisUtilTestController {

    @Autowired
    private RedisStringUtil redisStringUtil;

    @GetMapping("setDomain")
    public String addDomainTest(){
        User user = new User();
        user.setId(1L);
        user.setName("hesuijin");
        user.setAge(18);

        redisStringUtil.setObject("user1",user);
        return "set success";
    }

    @GetMapping("getDomain")
    public String getDomainTest(){

        User user = redisStringUtil.getObject("user1",User.class);

//        User(id=1, name=hesuijin, age=18)
        return user.toString();

//        {"age":18,"id":1,"name":"hesuijin"}
//        return JSONObject.toJSONString(user);
    }
}
