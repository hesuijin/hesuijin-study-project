package com.example.jwt.junit;

import com.example.jwt.base.request.LoginRequest;
import com.example.jwt.secuirty.controller.AuthController;
import com.example.jwt.secuirty.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/5
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthControllerJunit {

    @Autowired
    private AuthController authController;

    /**
     * 用户登录
     * login单元测试
     */
    @Test
    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("HeSuiJin");
        loginRequest.setPassword("123456");
        authController.login(loginRequest);
    }

}
