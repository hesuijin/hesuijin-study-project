package com.example.demo;

import com.example.demo.aspect.AspectTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/30
 */

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class AspectJunit {

    @Autowired
   private AspectTest aspectTest;

    @Test
    public void AspectTestAround(){
        aspectTest.aspectMethodTestOne();

        aspectTest.aspectMethodTestTwo();
    }
}