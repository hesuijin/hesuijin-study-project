package com.example.rocketmq.producer.junit;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author HeSuiJin
 * @Date 2021/3/1 23:23
 * @Description:
 */
@RocketMQMessageListener(topic = "HELLO_TOPIC",consumerGroup = "${rocketmq.producer.group}")
@Component
public class ConsumerDemo implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("接收的消息："+s);
    }
}
