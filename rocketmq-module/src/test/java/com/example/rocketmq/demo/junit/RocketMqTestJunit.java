package com.example.rocketmq.demo.junit;

import com.example.rocketmq.demo.producer.controller.PayController;
import com.example.rocketmq.demo.transactionRocketMQ.TransactionProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.store.LocalFileOffsetStore;
import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author HeSuiJin
 * @Date 2021/3/14 12:20
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RocketMqTestJunit {

    @Autowired
    private PayController payController;

    @Test
    public void payControllerJunit()  {
        payController.callback("你好啊","my_tag");
    }

    @Autowired
    private TransactionProducer transactionProducer;
    /**
     *  RocketMQ分布式事务消息测试  消费发送
     * @throws MQClientException
     * @throws InterruptedException
     */
    @Test
    public void sendMessageInTransaction() throws MQClientException, InterruptedException {
        transactionProducer.sendMessageInTransaction();
    }
}
