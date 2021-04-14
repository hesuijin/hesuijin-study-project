package com.example.rocketmq.demo.junit.RocketMqProducerDemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.rocketmq.demo.base.Order;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * 顺序投递测试
 * @Author HeSuiJin
 * @Date 2021/4/14
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class ProducerSendSequence {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    /**
     *构建orderList
     * @return
     */
    private static List<Order> getOrderList(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1L,"创建订单"));
        orderList.add(new Order(2L,"创建订单"));
        orderList.add(new Order(2L,"订单预提交"));
        orderList.add(new Order(1L,"订单预提交"));
        orderList.add(new Order(1L,"订单完成"));
        orderList.add(new Order(2L,"订单完成"));
        orderList.add(new Order(2L,"订单退款"));
        orderList.add(new Order(1L,"订单退款"));
        return orderList;
    }

    /**
     * 进行发送
     */
    @Test
    public void  producerSendCallbackJunit() {
       List<Order> orderList = getOrderList();
        orderList.forEach(order -> {
            RocketEvent rocketEvent = new RocketEvent<>("event",order);
            this.sendOrderMessage(rocketEvent);
        });

    }


    private void  sendOrderMessage(RocketEvent<?> rocketEvent) {
        //主题
        String topic = "pay_Sequence_topic";
        Message message ;
        try {
            if (rocketEvent.getTag() != null && !"".equals(rocketEvent.getTag()) ) {
                message = new Message(topic,rocketEvent.getTag(), JSON.toJSONString(rocketEvent).getBytes());
            }else {
                message = new Message(topic, JSON.toJSONString(rocketEvent).getBytes());
            }
            log.info("RocketMQ order 消息发送：{}", JSON.toJSONString(rocketEvent));

            //转换为Order
            Order  order = JSONObject.parseObject(JSONObject.toJSONString(rocketEvent.getData()), Order.class);

            //顺序发送可以根据一个订单号进行取模 然后该订单选择对应的队列

            //public SendResult send(Message msg, MessageQueueSelector selector, Object arg)
            //同步发送
            //选择对应的队列
            SendResult sendResultSyn =  defaultMQProducer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> messageQueueList, Message message, Object arg) {
                   Long orderId = (Long) arg;
                   //根据 订单Id 取模后循序投递到队列
                   long queueNum =  orderId%messageQueueList.size();
                   return messageQueueList.get((int) queueNum);
                }
                // 取订单Id作为入参
            },order.getOrderId());

            log.info("RocketMQ order 消息同步发送 结果：{}",  JSON.toJSONString(sendResultSyn));


        } catch (Exception e) {
            log.error("RocketMQ order 消息发送异常: " + e.getMessage(), e);
        }
    }

}
