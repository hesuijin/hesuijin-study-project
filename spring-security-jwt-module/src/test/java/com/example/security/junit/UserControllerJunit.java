package com.example.security.junit;

import com.example.security.base.request.UserRegisterRequest;
import com.example.security.mvc.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 18:42
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerJunit {

    @Autowired
    private UserController userController;

    /**
     * 注册用户
     * save单元测试
     */
    @Test
    public void save(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("root");
        userRegisterRequest.setFullName("HSJ");
        userRegisterRequest.setPassword("123456");
        userController.signUp(userRegisterRequest);
    }

}
