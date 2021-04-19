package com.example.rocketmq.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.consumer.store.LocalFileOffsetStore;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.factory.MQClientInstance;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import static org.apache.rocketmq.common.protocol.heartbeat.MessageModel.CLUSTERING;

/**
 * @Author HeSuiJin
 * @Date 2021/3/19 11:07
 * @Description:
 */
@Slf4j
@Component
public class RocketMqConsumerListener {

    private String pay_consumer_group = "pay_consumer_group";

    private String nameSrvAddr = "47.113.101.241:9876";

    private String topic = "pay_test_topic";


    public RocketMqConsumerListener() throws MQClientException {

        //创建DefaultMQPushConsumer
        DefaultMQPushConsumer  defaultMQPushConsumer = creatDefaultMQPushConsumer();

        //监听器
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            try {
                Message msg = messages.get(0);
                log.info(" Receive New Messages: {},{} ",Thread.currentThread().getName(), new String(messages.get(0).getBody()));
                String topic = msg.getTopic();
                String tags = msg.getTags();
                String keys = msg.getKeys();
                String body = new String(msg.getBody(), "utf-8");
                log.info("topic=" + topic + ", tags=" + tags + ", keys = " + keys + ", msg = " + body);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (UnsupportedEncodingException e) {
                log.info("rocket消费者异常：{}",e.getMessage(),e);
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
        DefaultMQPushConsumer  defaultMQPushConsumer = new DefaultMQPushConsumer();
        //如果是集群模式 以 ; 分开   "IP1:9876;IP2:9876;"
        defaultMQPushConsumer.setNamesrvAddr(nameSrvAddr);
        //设置消费者存放的组
        defaultMQPushConsumer.setConsumerGroup(pay_consumer_group);
        //订阅的主题 topic
        defaultMQPushConsumer.subscribe(topic, "*");

//        消费者消费模式
        defaultMQPushConsumer.setMessageModel(CLUSTERING);
//        offsetStore:消息消费进度存储器 （现在已经不允许设置）
//        defaultMQPushConsumer.setOffsetStore(RemoteBrokerOffsetStore);
//
//        队列初次创建时候 从哪里开始消费
//        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//
//        负载均衡策略算法  消费者分配队列的算法  默认就是AllocateMessageQueueAveragely 即取模平均分配
//        defaultMQPushConsumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragely());
//
//        最小消费线程池数量
//        defaultMQPushConsumer.setConsumeThreadMin(20);
//        最大消费线程池数量
//        defaultMQPushConsumer.setConsumeThreadMax(20);
//
//       一次性拉取数据的数量
//        defaultMQPushConsumer.setPullBatchSize(32);

        return defaultMQPushConsumer;
    }
}
