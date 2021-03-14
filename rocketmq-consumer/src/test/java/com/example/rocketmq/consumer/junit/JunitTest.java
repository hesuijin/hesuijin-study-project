package com.example.rocketmq.consumer.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author HeSuiJin
 * @Date 2021/3/14 12:20
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JunitTest {

    @Test
    public void junitTest(){
        System.out.println("ok");
    }

}
