package com.example.rocketmq.demo.junit.RocketMqProducerDemo;

import com.alibaba.fastjson.JSON;
import com.example.rocketmq.demo.base.RocketEvent;
import com.example.rocketmq.demo.component.RocketMqProduceComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/13
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class ProducerSendCallback {

    @Autowired
    private DefaultMQProducer defaultMQProducer;


    @Test
    public void  producerSendCallbackJunit() {
        RocketEvent rocketEvent = new RocketEvent<>("event","Hello Rocket MQ ："+"我是测试异步发送消息啊啊");
        this.sendOrderMessage(rocketEvent);
    }


    private void  sendOrderMessage(RocketEvent<?> rocketEvent) {
        //主题
        String topic = "pay_test_topic";
        Message message ;
        try {
            if (rocketEvent.getTag() != null && !"".equals(rocketEvent.getTag()) ) {
                message = new Message(topic,rocketEvent.getTag(), JSON.toJSONString(rocketEvent).getBytes());
            }else {
                message = new Message(topic, JSON.toJSONString(rocketEvent).getBytes());
            }
            log.info("RocketMQ order 消息发送：{}", JSON.toJSONString(rocketEvent));
            //异步异步发送消息（需求要求速度非常高的时候 ）
            //结果异步返回
            defaultMQProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("RocketMQ order 消息异步发送结果：{}", JSON.toJSONString(sendResult));
                    //发送成功可以触发其他的逻辑
                }

                @Override
                public void onException(Throwable throwable) {
                    log.info("异步发送返回异常:"+throwable,throwable.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("RocketMQ order 消息发送异常: " + e.getMessage(), e);
        }
    }
}
