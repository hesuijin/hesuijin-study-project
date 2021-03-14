package com.example.rocketmq.producer.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RocketMQJunit {

    @Autowired
    private ProducerDemo producerDemo;

    @Test
    public void pushMsgJunit()  {
        producerDemo.sendMessage();
    }

}
