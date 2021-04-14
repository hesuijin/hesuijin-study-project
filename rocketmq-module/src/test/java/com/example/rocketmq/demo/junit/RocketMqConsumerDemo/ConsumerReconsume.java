package com.example.rocketmq.demo.junit.RocketMqConsumerDemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author HeSuiJin
 * @Date 2021/4/13 12:20
 * @Description:
 * 解决重复消费
 *
 *  消费者
 *  1：设置重试次数
 *  2：消费端去重：一条消息无论重试多少次，这些重试消息的Message ID, key不会改变。
 *   3：消费重试只针对集群消费方式生效；广播方式不提供失败重试特性，即消费失败后，失败消息不再重试
 * Rocket消费端 消费重试
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class ConsumerReconsume {

    private String pay_consumer_group = "pay_consumer_group";

    private String nameSrvAddr = "47.113.101.241:9876";

    private String topic = "pay_test_topic";

    @Test
    public void rocketMqConsumerReconsumeJunit() throws MQClientException {

         DefaultMQPushConsumer defaultMQPushConsumer = creatDefaultMQPushConsumer();

            //监听器
            defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {

                    MessageExt msg = messages.get(0);
                    int times = msg.getReconsumeTimes();
                    System.out.println("重试次数："+times);
                try {
                    log.info(" Receive New Messages: {},{} ",Thread.currentThread().getName(), new String(messages.get(0).getBody()));
                    String topic = msg.getTopic();
                    String tags = msg.getTags();
                    String keys = msg.getKeys();
                    String body = new String(msg.getBody(), "utf-8");
                    log.info("topic=" + topic + ", tags=" + tags + ", keys = " + keys + ", msg = " + body);

                    //使消费一定失败
                    if(true){
                        throw new Exception();
                    }

                    System.out.println("重试时的Id"+ msg.getMsgId());

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (Exception e) {
                    log.info("rocket消费者异常：{}",e.getMessage(),e);

                    //times的初始值为0 等于等于2就是一件尝试了3次
                    if (times>=2){
                        System.out.println("记录数据库 通知运维人员");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }

                    System.out.println("重试次数异常:"+times);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
            log.info("consumer  start");
            defaultMQPushConsumer.start();
        }


    /**
     * 配置DefaultMQPushConsumer参数
     * @throws MQClientException
     */
    private DefaultMQPushConsumer creatDefaultMQPushConsumer() throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
        //如果是集群模式 以 ; 分开   "IP1:9876;IP2:9876;"
        defaultMQPushConsumer.setNamesrvAddr(nameSrvAddr);
        //消费时的策略
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //设置消费者存放的组
        defaultMQPushConsumer.setConsumerGroup(pay_consumer_group);
        //订阅的主题 topic
        defaultMQPushConsumer.subscribe(topic, "*");
        return defaultMQPushConsumer;
    }
}
