package com.example.rocketmq.demo.junit.RocketMqProducerDemo;

import com.alibaba.fastjson.JSON;
import com.example.rocketmq.demo.base.RocketEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/13
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class ProducerSendQueueSelector {

    @Autowired
    private DefaultMQProducer defaultMQProducer;


    @Test
    public void  producerSendCallbackJunit() {
        RocketEvent rocketEvent = new RocketEvent<>("event","Hello Rocket MQ ："+"我是测试 选择队列发送 ");
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

            //顺序发送可以根据一个订单号进行取模 然后该订单选择对应的队列
            //与业务是强耦合的 需要根据业务做具体的分析

            //public SendResult send(Message msg, MessageQueueSelector selector, Object arg)
            //同步发送
            //选择对应的队列
           SendResult sendResultSyn =  defaultMQProducer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> messageQueueList, Message message, Object arg) {
                   //2：选择使用该topic下的哪个  queueId   该Id需要在 arg获取
                    int queueNum = Integer.parseInt(arg.toString());
                    return messageQueueList.get(queueNum);
                }
                // 1：arg 0 为选择队列  queueId =0
            },0);

            log.info("RocketMQ order 消息同步发送 结果：{}",  JSON.toJSONString(sendResultSyn));

            //public void send(Message msg, MessageQueueSelector selector, Object arg, SendCallback sendCallback)
            //异步发送
            //选择对应的队列
             defaultMQProducer.send(message, (messageQueueList, message1, arg) -> {
                int queueNum = Integer.parseInt(arg.toString());
                return messageQueueList.get(queueNum);
            }, 1, new SendCallback() {

                @Override
                public void onSuccess(SendResult sendResultAsyn) {
                    log.info("RocketMQ order 消息异步发送 结果：{}",  JSON.toJSONString(sendResultAsyn));
                }
                @Override
                public void onException(Throwable throwable) {

                }
            });

        } catch (Exception e) {
            log.error("RocketMQ order 消息发送异常: " + e.getMessage(), e);
        }
    }
}
