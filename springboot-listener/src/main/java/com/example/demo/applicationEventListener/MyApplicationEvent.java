package com.example.demo.applicationEventListener;

import com.example.demo.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

/**
 * @Description:
 * 自定义事件需要继承 ApplicationEvent
 * @Author HeSuiJin
 * @Date 2021/4/30
 */
public class MyApplicationEvent extends ApplicationEvent {

    private User user;

    public User getUser() {
        return user;
    }

    public MyApplicationEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

}
