package com.example.rocketmq.producer.component;

import com.alibaba.fastjson.JSON;
import com.example.rocketmq.producer.base.RocketEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

/**
 * @Author HeSuiJin
 * @Date 2021/3/18 22:20
 * @Description:
 */
@Slf4j
@Component
public class RocketMqComponent {

    private DefaultMQProducer defaultMQProducer;

    public RocketMqComponent(DefaultMQProducer defaultMQProducer) {
        this.defaultMQProducer = defaultMQProducer;
    }

    public void sendOrderMessage(RocketEvent<?> rocketEvent) {
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
            SendResult sendResult = defaultMQProducer.send(message);
            log.info("RocketMQ order 消息发送结果：{}", JSON.toJSONString(sendResult));
        } catch (Exception e) {
            log.error("RocketMQ order 消息发送异常: " + e.getMessage(), e);
        }
    }

    /**
     * 一般应用上下文 使用上下文监听器 进行关闭
     */
    public  void  shutdown(){
        this.defaultMQProducer.shutdown();
    }
}
