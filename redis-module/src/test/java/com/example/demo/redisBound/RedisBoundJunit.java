package com.example.demo.redisBound;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * boundValueOps()  类似于  redisTemplate.opsForValue() (原生)
 * boundHashOps()   类型于   redisTemplate.opsForHash() (原生)
 *
 * 另外还有boundListOps()、boundSetOps()、boundZSetOps()
 *
 * 建议是使用以redisTemplate.opsForValue() 为主 因为更加贴近redis的原生语言     b
 *
 * 注意哪个好用用哪个
 * @Author HeSuiJin
 * @Date 2021/4/12
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisBoundJunit {
    @Autowired
    StringRedisTemplate redisTemplate;


    /**
     * 类似于 redisTemplate.opsForValue()
     */
    @Test
    public void boundValueOpsTest() {

        //针对于某个key
        BoundValueOperations<String, String>  boundValueOperations = redisTemplate.boundValueOps("String");
        boundValueOperations.set("string");

        System.out.println(boundValueOperations.get());
    }


    /**
     * 类似于 redisTemplate.opsForHash()
     */
    @Test
    public void boundHashOperationsTest() {

        //针对于某个key
        BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps("hash");
        boundHashOperations.put("hashKey1","hashValue1");
        boundHashOperations.put("hashKey2","hashValue2");
        boundHashOperations.put("hashKey3","hashValue3");

        System.out.println(boundHashOperations.get("hashKey1"));
        System.out.println(boundHashOperations.get("hashKey2"));
        System.out.println(boundHashOperations.get("hashKey3"));

        System.out.println("keys"+boundHashOperations.keys());
        System.out.println("values"+boundHashOperations.values());
        System.out.println("entries"+boundHashOperations.entries());
    }

    /**
     * redisTemplate的删除操作非常方便
     */
    @Test
    public void redisTemplate(){
        redisTemplate.delete("hash");
    }
}
