package com.example.rocketmq.demo.producer.controller;

import com.example.rocketmq.demo.base.RocketEvent;
import com.example.rocketmq.demo.transactionRocketMQ.TransactionProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * 测试RocketMq分布式接口
 * @Author HeSuiJin
 * @Date 2021/4/20
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionProducer transactionProducer;

    @RequestMapping("/api/v1/transaction")
    public String rocketMQTransactionTest() throws MQClientException, InterruptedException {

        transactionProducer.sendMessageInTransaction();
        return "success";
    }
}
