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
    public String setDomainTest(){
        User user = new User();
        user.setId(1L);
        user.setName("hesuijin");
        user.setAge(18);

        redisStringUtil.setObject("user",user);
        return "set success";
    }

    @GetMapping("getDomain")
    public String getDomainTest(){
        User user = redisStringUtil.getObject("user",User.class);

//         {"age":18,"id":1,"name":"hesuijin"}
        return JSONObject.toJSONString(user);

//        User(id=1, name=hesuijin, age=18)
//        return user.toString();
    }

    /**
     *  使用分号 : 区分多级
     * @return
     */
    @GetMapping("setMulDomain")
    public String setMulDomainTest(){
        User user1 = new User();
        user1.setId(1L);
        user1.setName("hesuijin");
        user1.setAge(18);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("HSJ");
        user2.setAge(18);

        redisStringUtil.setObject("redisUtil:user1",user1);
        redisStringUtil.setObject("redisUtil:user2",user1);
        return "set success";
    }

    /**
     * 需要分开获取  不能直接获取整个包
     * @return
     */
    @GetMapping("getMulDomain")
    public String getMulDomainTest(){
        String returnString1 = redisStringUtil.getString("redisUtil:user1");
        String returnString2 = redisStringUtil.getString("redisUtil:user2");

        return  "返回1:"+returnString1+ "返回2："+returnString2;

    }
}
