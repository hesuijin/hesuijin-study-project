package com.example.demo.concurrenctLock.visibleSynchronizedDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MoneySynchronized {

    public static Object lock = new Object();
    public static  int money = 10;
}
