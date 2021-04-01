package com.example.demo.redisZSet;

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
public class RedisZSetJunit {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void zAddTest(){
        //权重可以为double类型 权重越大 排序越后  若权重一样则按照ASCII

        redisTemplate.opsForZSet().add("ZSet1","1",1);
        redisTemplate.opsForZSet().add("ZSet1","2",2);
        redisTemplate.opsForZSet().add("ZSet1","3",3);

        redisTemplate.opsForZSet().add("ZSet2","3",1);
        redisTemplate.opsForZSet().add("ZSet2","4",2);
        redisTemplate.opsForZSet().add("ZSet2","5",3);
        log.info("zAddTest 请求返回：{}","success");
    }

    @Test
    public void getZSetRangeTest(){
        Set zSetRange1 =redisTemplate.opsForZSet().range("ZSet1",0,-1);
        Set zSetRange2 =redisTemplate.opsForZSet().range("ZSet2",0,-1);

        log.info("getSetRangeTest zSetRange1 请求返回：{}", JSONObject.toJSONString(zSetRange1));
        log.info("getSetRangeTest zSetRange2 请求返回：{}", JSONObject.toJSONString(zSetRange2));
    }

    @Test
    public void getZSetScoreTest(){
        redisTemplate.opsForZSet().add("Zet","11",1);
        redisTemplate.opsForZSet().add("Zet","22",2);
        redisTemplate.opsForZSet().add("Zet","33",3);
        redisTemplate.opsForZSet().add("Zet","44",3);
        redisTemplate.opsForZSet().add("Zet","55",3);

        //根据权重获取值
        Set rangeByScore =redisTemplate.opsForZSet().rangeByScore("Zet",0,1);
        log.info("getZSetScoreTest rangeByScore 请求返回：{}", JSONObject.toJSONString(rangeByScore));

        //获取权重 在[2,3]之间的值 然后 1+1 第二位开始 获取 2个值
        Set rangeByScoreOffset =redisTemplate.opsForZSet().rangeByScore("Zet",2,3,1,2);
        log.info("getZSetScoreTest rangeByScore 请求返回：{}", JSONObject.toJSONString(rangeByScoreOffset));
    }

    @Test
    public void deleteTest(){
        redisTemplate.opsForZSet().add("ZSet","1",1);
        redisTemplate.opsForZSet().add("ZSet","2",2);
        redisTemplate.opsForZSet().add("ZSet","3",3);

        Set zSetRange =redisTemplate.opsForZSet().range("ZSet",0,-1);
        log.info("getSetRangeTest zSetRange 请求返回：{}", JSONObject.toJSONString(zSetRange));

        redisTemplate.opsForZSet().remove("ZSet","0","1");
        Set zSetRangeRemove =redisTemplate.opsForZSet().range("ZSet",0,-1);
        log.info("getZSetRangeTest zSetRangeRemove 请求返回：{}", JSONObject.toJSONString(zSetRangeRemove));
    }

    /**
     *  交集  并集
     */
    @Test
    public void IntersectTest_Union(){
        //交集
        Long a =redisTemplate.opsForZSet().intersectAndStore("Set1","Set2","intersectRange");
        log.info("intersectAndStore  请求返回交集个数：{}", JSONObject.toJSONString(a));
        Set intersectRange =redisTemplate.opsForZSet().range("intersectRange",0,-1);
        log.info("intersectMember zSetRange 请求返回：{}", JSONObject.toJSONString(intersectRange));

        //并集
        Long b =redisTemplate.opsForZSet().unionAndStore("Set1","Set2","unionRange");
        log.info("unionAndStore  请求返回并集个数：{}", JSONObject.toJSONString(b));
        Set unionRange =redisTemplate.opsForZSet().range("unionRange",0,-1);
        log.info("unionRange zSetRange 请求返回：{}", JSONObject.toJSONString(unionRange));

    }

}
