package com.example.demo.applicationEventListenerTest;

import com.example.demo.applicationEventListener.MyApplicationEventPublish;
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
public class MyApplicationEventPublishJunit {

    @Autowired
    private MyApplicationEventPublish myApplicationEventPublish;

    @Test
    public void myApplicationEventPublishJunit(){
        myApplicationEventPublish.ApplicationEventPublish();
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
