package com.example.demo.redisHash;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/3/31
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisHashJunit {

        @Autowired
        StringRedisTemplate redisTemplate;

        @Test
        public void putTest(){
            User user = new User();
            user.setId(1L);
            user.setAge(18);
            user.setName("HSJ");
            redisTemplate.opsForHash().put("hash","hashKey1","hashValue1");
            redisTemplate.opsForHash().put("hash","hashKey2","hashValue2");
            redisTemplate.opsForHash().put("hash","hashKey3","hashValue3");

            //报错 User cannot be cast to java.lang.String
            //redisTemplate.opsForHash().put("hash","user",user);
            //注意toString后是没有办法转换回来的
            //redisTemplate.opsForHash().put("hash","user",user.toString());

            //报错.JSONObject cannot be cast to java.lang.String
            //redisTemplate.opsForHash().put("hash","userJSONString",JSONObject.toJSON(user));
            redisTemplate.opsForHash().put("hash","userJSONString",JSONObject.toJSON(user).toString());
            log.info("putTest 请求返回：{}","success");
        }

    @Test
    public void getTest()  {
//       Object object1= redisTemplate.opsForHash().get("hash","user");
//        log.info("getTest 请求返回 转换前：{}", JSONObject.toJSONString(object1));
//        User user1 = (User)(object1);
//        log.info("getTest 请求返回 转换后：{}", JSONObject.toJSONString(user1));

        Object object2= redisTemplate.opsForHash().get("hash","userJSONString");
        log.info("getTest 请求返回 转换前：{}", JSONObject.toJSONString(object2));

        User user2 = JSONObject.parseObject(object2.toString(),User.class);
        log.info("getTest 请求返回 转换后：{}", JSONObject.toJSONString(user2));
    }

    /**
     * 获取所有key value
     */
    @Test
    public void getAllTest()  {
        Object object = redisTemplate.opsForHash().entries("hash");
        log.info("getAllTest 请求返回 所有 hashMap：{}", JSONObject.toJSONString(object));
    }

    /**
     * 获取 该key的哈希表中 所有的字段名或字段值
     */
    @Test
    public void keysOrValueTest()  {
        Object object1= redisTemplate.opsForHash().keys("hash");
        log.info("keysOrValueTest 请求返回 所有keys：{}", JSONObject.toJSONString(object1));
        Object object2= redisTemplate.opsForHash().values("hash");
        log.info("keysOrValueTest 请求返回 所有values：{}", JSONObject.toJSONString(object2));
    }
}
