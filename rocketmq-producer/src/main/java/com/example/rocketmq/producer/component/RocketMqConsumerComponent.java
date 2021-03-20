package com.example.rocketmq.producer.component;

import com.alibaba.fastjson.JSONObject;
import com.example.rocketmq.producer.config.RocketMqConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * @Author HeSuiJin
 * @Date 2021/3/19 11:07
 * @Description:
 */
@Slf4j
@Configuration
public class RocketMqConsumerComponent {

    private String pay_consumer_group = "pay_consumer_group";

    private DefaultMQPushConsumer consumer;

    public RocketMqConsumerComponent() throws MQClientException {
         consumer = new DefaultMQPushConsumer();
        //服务器地址
        consumer.setNamesrvAddr("47.113.101.241:9876");
        //消费时的策略
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //设置消费者存放的组
        consumer.setConsumerGroup(pay_consumer_group);
        //订阅的主题 topic
        consumer.subscribe("pay_test_topic", "*");

        //监听器
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            try {
                Message msg = messages.get(0);
                log.info(" Receive New Messages: {},{} ",Thread.currentThread().getName(), new String(messages.get(0).getBody()));
                String topic = msg.getTopic();
                String body = new String(msg.getBody(), "utf-8");
                String tags = msg.getTags();
                String keys = msg.getKeys();
                log.info("topic=" + topic + ", tags=" + tags + ", keys = " + keys + ", msg = " + body);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (UnsupportedEncodingException e) {
                log.info("rocket消费者异常：{}",e.getMessage(),e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        log.info("consumer  start");
        consumer.start();
    }
}
