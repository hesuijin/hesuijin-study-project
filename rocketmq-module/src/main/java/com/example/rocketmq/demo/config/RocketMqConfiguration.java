package com.example.rocketmq.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 读取配置
 * @Author HeSuiJin
 * @Date 2021/3/18 18:59
 * @Description:
 */
@Slf4j
@Configuration
public class RocketMqConfiguration {

    @Value("${rocketmq.name-srv-addr}")
    private String nameSrvAddr;
    @Value("${rocketmq.producer-group}")
    private String producerGroup;

    /**
     * 创建普通消息发送者实例 替换 DefaultMQProducer 的Bean对象
     * @return 返回普通消息发送者实例
     * @throws MQClientException MQ客户端异常
     */
    @Bean
    @Scope("prototype")
    public DefaultMQProducer defaultProducer() throws MQClientException {
        log.info("初始化RocketMQ");
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();

        //该生产者所在group
        defaultMQProducer.setProducerGroup(producerGroup);
        //如果是集群模式 以 ; 分开  为namesrvAddr地址   "IP1:9876;IP2:9876;"
        defaultMQProducer.setNamesrvAddr(nameSrvAddr);
        //是否走Vip通道
        defaultMQProducer.setVipChannelEnabled(false);
        //设置发送失败重试次数
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(3);

        //消息同步发送失败重试次数
        defaultMQProducer.setRetryTimesWhenSendFailed(3);
        //消息异步发送失败重试次数
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(3);

//        消息超过默认字节4096后进行压缩
//        defaultMQProducer.setCompressMsgBodyOverHowmuch(4096);
//        最大消息配置，默认128k
//        defaultMQProducer.setMaxMessageSize(12);
//        自动创建服务器不存在的Topic,默认创建的队列数
//        defaultMQProducer.setDefaultTopicQueueNums(4);

        //开启线程
        defaultMQProducer.start();
        return defaultMQProducer;
    }

}
