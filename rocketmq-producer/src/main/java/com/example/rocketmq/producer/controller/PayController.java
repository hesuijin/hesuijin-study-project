package com.example.rocketmq.producer.controller;

import com.example.rocketmq.producer.base.RocketEvent;
import com.example.rocketmq.producer.component.CallBackComponent;
import com.example.rocketmq.producer.component.RocketMqProduceComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author HeSuiJin
 * @Date 2021/3/18 14:10
 * @Description:
 */
@RestController
public class PayController {


    @Autowired
    private RocketMqProduceComponent rocketMqProduceComponent;
    @Autowired
    private CallBackComponent callBackComponent;

    @RequestMapping("/api/v1/pay")
    public Object callback(String message) {

        //事务提交后再发送
        callBackComponent.execute(()->{
            RocketEvent rocketEvent = new RocketEvent<>("event","Hello Rocket MQ"+message);
            rocketMqProduceComponent.sendOrderMessage(rocketEvent);
        });
        return null;
    }
}
