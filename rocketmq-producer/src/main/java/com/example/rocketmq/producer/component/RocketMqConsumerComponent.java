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
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Author HeSuiJin
 * @Date 2021/3/19 11:07
 * @Description:
 */
@Slf4j
@Component
public class RocketMqConsumerComponent {

    private String pay_consumer_group = "pay_consumer_group";

     //猜测是spring的加载信息出现了问题   @Component 比@Bean先 然后没有获取到@Bean的信息
//     private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

    //可以获取对象  但里面bean 并没有读取到yml配置文件
//    private DefaultMQPushConsumer consumer = new RocketMqConfiguration().DefaultPushConsumer();
    @Autowired
    private DefaultMQPushConsumer consumer;

    public RocketMqConsumerComponent() throws MQClientException {
//        consumer = new DefaultMQPushConsumer(consumerGroup);

        //这个时候打印spring bean的值还没进去
        log.info("打印{}",JSONObject.toJSONString(consumer.getConsumerGroup()));
//        服务器地址
        consumer.setNamesrvAddr("47.113.101.241:9876");
//        消费时的策略
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.setConsumerGroup(pay_consumer_group);
        //订阅的主题 topic
        consumer.subscribe("pay_test_topic", "*");

        //监听器
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            try {
                Message msg = messages.get(0);
                System.out.printf("%s Receive New Messages: %s %n",
                        Thread.currentThread().getName(), new String(messages.get(0).getBody()));
                String topic = msg.getTopic();
                String body = new String(msg.getBody(), "utf-8");
                String tags = msg.getTags();
                String keys = msg.getKeys();
                System.out.println("topic=" + topic + ", tags=" + tags + ", keys = " + keys + ", msg = " + body);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        System.out.println("consumer  start");
        consumer.start();
    }
}
