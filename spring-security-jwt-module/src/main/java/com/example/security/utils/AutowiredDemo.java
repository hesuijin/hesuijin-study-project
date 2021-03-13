package com.example.security.utils;


import com.example.security.base.request.LoginRequest;
import com.example.security.mvc.serviceImpl.AuthServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class AutowiredDemo{
//    @Autowired
    private AuthServiceImpl authService ;

    public AutowiredDemo() {

    }

   public void test(){
        System.out.println(authService);
        System.out.println("1111");
       System.out.println("2222");
       LoginRequest loginRequest = new LoginRequest();
       loginRequest.setUsername("root");
       loginRequest.setPassword("123456");
       loginRequest.setRememberMe(false);
       authService.createToken(loginRequest);
   }


 }