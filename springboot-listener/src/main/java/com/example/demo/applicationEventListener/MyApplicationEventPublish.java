package com.example.demo.applicationEventListener;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/30
 */
@Component
public class MyApplicationEventPublish {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    public void ApplicationEventPublish(){
        User user = new User();
        user.setId(1L);
        user.setName("hesuijin");
        user.setAddress("广州");

        //构造自定义事件
        MyApplicationEvent myApplicationEvent = new MyApplicationEvent(this,user);

        //使用applicationEventPublisher进行方便

        //监听者有两种方式进行监听
        //1：监听者实现了  ApplicationListener<MyApplicationEvent>   类
        //2: 监听者添加了  @EventListener  注解到 public void onApplicationEvent(MyApplicationEvent myApplicationEvent)
        applicationEventPublisher.publishEvent(myApplicationEvent);

        System.out.println("你好啊 我已经经过了监听方法了");
        //applicationContext 继承了applicationEventPublisher 以及其他的很多类
//        applicationContext.publishEvent(myApplicationEvent);
    }

}
