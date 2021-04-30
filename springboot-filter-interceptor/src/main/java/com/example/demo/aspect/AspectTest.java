package com.example.demo.aspect;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/30
 */
@Service
public class AspectTest {

    /**
     * 已经被Aspect切面方式  @Around 环绕
     */
    public void aspectMethodTestOne(){
        System.out.println("我是 aspectMethodTestOne  ");
    }

    /**
     * 已经被Aspect切面方式  @Pointcut定义为切点 并设置 before 与 after逻辑
     */
    public void aspectMethodTestTwo(){
        System.out.println("我是 aspectMethodTestTwo  ");
    }
}
