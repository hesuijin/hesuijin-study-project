package com.example.rocketmq.producer.junit;

import com.example.rocketmq.producer.controller.PayController;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
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
public class JunitTest {

    @Autowired
    private PayController payController;

    @Test
    public void payControllerJunit() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        payController.callback("hhhh");
    }

}
