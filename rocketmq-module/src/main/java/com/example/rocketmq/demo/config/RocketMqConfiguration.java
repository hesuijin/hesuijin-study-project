package com.example.rocketmq.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public DefaultMQProducer defaultProducer() throws MQClientException {
        log.info("初始化RocketMQ");
        //该生产者所在group
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(producerGroup);
        //如需要多个地址 以 ; 分开   "47.113.101.241:9876;127.0.0.1:9876;"
        defaultMQProducer.setNamesrvAddr(nameSrvAddr);
        //是否走Vip通道
        defaultMQProducer.setVipChannelEnabled(false);
        //设置发送失败重试次数
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(3);
        //开启线程
        defaultMQProducer.start();
        return defaultMQProducer;
    }

}
