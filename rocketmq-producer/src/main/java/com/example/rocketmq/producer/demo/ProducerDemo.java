package com.example.rocketmq.producer.demo;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author HeSuiJin
 * @Date 2021/3/1 23:19
 * @Description:
 */
@Component
public class ProducerDemo {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(){
        //参数1:topic  参数2：要发送的消息
        rocketMQTemplate.convertAndSend("HELLO_TOPIC","你好啊！");
        System.out.println("消息发送完成");
    }

}
