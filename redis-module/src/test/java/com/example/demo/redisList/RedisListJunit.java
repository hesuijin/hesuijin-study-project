package com.example.demo.redisList;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/1
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisListJunit {

    @Autowired
    StringRedisTemplate redisTemplate;



    @Test
    public void pushTest(){
        redisTemplate.opsForList().leftPush("List","我是左边插入的1");
        redisTemplate.opsForList().leftPush("List","我是左边插入的2");
        redisTemplate.opsForList().rightPush("List","我是右边插入的1");
        redisTemplate.opsForList().rightPush("List","我是右边插入的2");
        log.info("pushTest 请求返回：{}","success");
    }


    /**
     *通过set替换数据
     */
    @Test
    public void setTest() {
        //使用下标获取数据
        redisTemplate.opsForList().set("List", 2, "我是通过set插入的");
        log.info("setTest 请求返回：{}", "success");
    }

    /**
     *通过    pushPivot   插入数据
     */
    @Test
    public void pushPivotTest() {
        //使用下标获取数据
        redisTemplate.opsForList().leftPush("List", "我是通过set插入的", "我是通过pushLeftPivot插入的");

        redisTemplate.opsForList().rightPush("List", "我是通过set插入的", "我是通过pushLeftPivot插入的");
        log.info("setTest 请求返回：{}", "success");
    }

    @Test
    public void deleteTest(){
        //删除 所有 "我是通过set插入的" 的元素
//        redisTemplate.opsForList().remove("List",0,"我是通过set插入的");

        //删除 count大于0  从左到右的第一个  "我是通过set插入的"
        redisTemplate.opsForList().remove("List",1,"我是通过set插入的");
        //删除 count大于0  从右到左的第一个  "我是通过set插入的"
        redisTemplate.opsForList().remove("List",-1,"我是通过set插入的");
        log.info("deleteTest 请求返回：{}","success");
    }



        /**
         * 获取数据
         */
    @Test
    public void indexTest(){
        //使用下标获取数据  0为第一个  -1为最后一个
        String indexReturn = redisTemplate.opsForList().index("List",0);
        log.info("indexTest 请求返回：{}",indexReturn);

        //TODO opsForList() 的  indexOf lastIndexOf  异常

//         redisTemplate.opsForList().indexOf("List","我是通过set插入的");
//        Long lastIndexOfReturn = redisTemplate.opsForList().lastIndexOf("List","我是通过set插入的");
//        log.info("我是通过set插入的 第一个地址：{}",indexOfReturn);
//        log.info("我是通过set插入的 最后一个地址：{}",lastIndexOfReturn);

        List list =  redisTemplate.opsForList().range("List",0,-1);
        log.info("range 请求返回：{}",list);
    }

    /**
     * 获取并移除数据
     */
    @Test
    public void popTest(){
      String popLeft =  redisTemplate.opsForList().leftPop("List");
      String popRight =  redisTemplate.opsForList().rightPop("List");
      log.info("popLeft 获取并移除的数据：{}",popLeft);
      log.info("popLeft 获取并移除的数据：{}",popLeft);
    }
}
