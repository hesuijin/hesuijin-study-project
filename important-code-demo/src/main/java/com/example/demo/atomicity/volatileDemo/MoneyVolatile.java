package com.example.demo.atomicity.volatileDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MoneyVolatile {

    //Volatile关键字 : 强制线程每次在使用的时候，都会看一下共享区域最新的值
    public static volatile int money = 10;
}
