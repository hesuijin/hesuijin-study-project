//package com.example.rocketmq.demo.transactionRocketMQ;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.TransactionMQProducer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
///**
// * @Description:
// * 分布式事务 配置 暂时注释
// * @Author HeSuiJin
// * @Date 2021/4/19
// */
//@Configuration
//@Slf4j
//public class RocketMqTransactionConfiguration {
//
////    由于TransactionMQProducer继承了DefaultMQProducer
////    不能出现两个DefaultMQProducer的Bean
////    expected single matching bean but found 2: defaultProducer,transactionMQProducer
//    @Bean
////    @Scope("prototype")
//    public TransactionMQProducer transactionMQProducer() throws MQClientException {
//        log.info("初始化分布式事务 RocketMQ");
//        TransactionMQProducer transactionMQProducer = new TransactionMQProducer();
//        //该生产者所在group
//        transactionMQProducer.setProducerGroup("transaction_producer_group");
//        ///如果是集群模式 以 ; 分开   "IP1:9876;IP2:9876;"
//        transactionMQProducer.setNamesrvAddr("47.113.101.241:9876");
//        //是否走Vip通道
//        transactionMQProducer.setVipChannelEnabled(false);
//        //设置发送失败重试次数
//        transactionMQProducer.setRetryTimesWhenSendAsyncFailed(3);
//
//        //消息同步发送失败重试次数
//        transactionMQProducer.setRetryTimesWhenSendFailed(3);
//        //消息异步发送失败重试次数
//        transactionMQProducer.setRetryTimesWhenSendAsyncFailed(3);
//
////        消息超过默认字节4096后进行压缩
//        transactionMQProducer.setCompressMsgBodyOverHowmuch(4096);
////        最大消息配置，默认128k
//        transactionMQProducer.setMaxMessageSize(12);
////        自动创建服务器不存在的Topic,默认创建的队列数
//        transactionMQProducer.setDefaultTopicQueueNums(4);
//
//        //开启线程
//        transactionMQProducer.start();
//        return transactionMQProducer;
//    }
//}
