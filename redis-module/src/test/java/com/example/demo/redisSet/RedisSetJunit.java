package com.example.demo.redisSet;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/1
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisSetJunit {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void addTest(){
        redisTemplate.opsForSet().add("Set1","1","2","3");

        redisTemplate.opsForSet().add("Set2","3","4","5");
        log.info("pushTest 请求返回：{}","success");
    }

    @Test
    public void getSetMemberTest(){
        Set setMember1 =redisTemplate.opsForSet().members("Set1");
        Set setMember2 =redisTemplate.opsForSet().members("Set2");

        log.info("getSetMemberTest setMember1 请求返回：{}", JSONObject.toJSONString(setMember1));
        log.info("getSetMemberTest setMember2 请求返回：{}", JSONObject.toJSONString(setMember2));
    }

    @Test
    public void deleteTest(){
        redisTemplate.opsForSet().add("Set","1","2","3","4","5" );
        Set setMember =redisTemplate.opsForSet().members("Set");
        log.info("getSetMemberTest setMember 请求返回：{}", JSONObject.toJSONString(setMember));

        redisTemplate.opsForSet().remove("Set","0","1","2");
        Set setMemberRemove =redisTemplate.opsForSet().members("Set");
        log.info("getSetMemberTest setMemberRemove 请求返回：{}", JSONObject.toJSONString(setMemberRemove));
    }

    /**
     *  交集  并集  差集
     */
    @Test
    public void IntersectTest_Union_Difference_(){
        //交集
        Set intersectMember =redisTemplate.opsForSet().intersect("Set1","Set2");
        log.info("intersectMember  请求返回：{}", JSONObject.toJSONString(intersectMember));
        redisTemplate.opsForSet().intersectAndStore("Set1","Set2","intersectMember");

        //并集
        Set unionMember = redisTemplate.opsForSet().union("Set1","Set2");
        log.info("unionMember  请求返回：{}", JSONObject.toJSONString(unionMember));
        redisTemplate.opsForSet().unionAndStore("Set1","Set2","unionMember");

        //差集 存在于前Set 而 不存在于后Set
        Set differenceMember =redisTemplate.opsForSet().difference("Set1","Set2");
        log.info("differenceMember  请求返回：{}", JSONObject.toJSONString(differenceMember));
        redisTemplate.opsForSet().differenceAndStore("Set1","Set2","differenceMember");
    }

}
