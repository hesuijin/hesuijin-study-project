package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/3/31
 */
@Component
public class RedisStringUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void  setString(String key ,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 直接存放对象
     * @param key
     * @param object
     */
    public void  setObject(String key ,Object object){
        redisTemplate.opsForValue().set(key,JSONObject.toJSONString(object));
    }

    public String getString(String key ){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 直接返回对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public   <T> T  getObject(String key ,Class<T> clazz){
        String returnString  = redisTemplate.opsForValue().get(key);
        if (null ==returnString){
            return null;
        }
        return JSONObject.parseObject(returnString, clazz);
    }
}
