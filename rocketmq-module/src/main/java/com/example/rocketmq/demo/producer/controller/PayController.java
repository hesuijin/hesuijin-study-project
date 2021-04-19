package com.example.rocketmq.demo.producer.controller;

import com.example.rocketmq.demo.base.RocketEvent;
import com.example.rocketmq.demo.component.CallBackComponent;
import com.example.rocketmq.demo.component.RocketMqProduceComponent;
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
    public Object callback(String message,String tag) {

        //事务提交后再发送
        callBackComponent.execute(()->{
            RocketEvent rocketEvent = new RocketEvent<>("event","Hello Rocket MQ ："+message,tag);
            rocketMqProduceComponent.sendOrderMessage(rocketEvent);
        });
        return null;
    }
}
