package com.example.demo.applicationEventListener;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/30
 */
@Component
@Slf4j
//也可以添加在MyApplicationEventListenerTwo
//@EnableAsync
public class MyApplicationEventListenerOne implements ApplicationListener<MyApplicationEvent> {

    @Override
    //添加该注解异步才生效
    @Async
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
        User user = myApplicationEvent.getUser();

        System.out.println("MyApplicationEventListenerOne  使用实现类方法");

        // 处理事件，实际项目中可以通知别的微服务或者处理其他逻辑等
        System.out.println("用户ID：" + user.getId());
        System.out.println("用户名称：" + user.getName());
        System.out.println("用户地址：" + user.getAddress());

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            log.info("MyApplicationEventListenerOne睡眠异常 {}"+e.getMessage(),e);
        }

        System.out.println("我已经执行完了MyApplicationEventListenerOne 逻辑");
    }
}
