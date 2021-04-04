package com.example.demo.atomicity.volatileDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MyThread2 extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MoneyVolatile.money = 9;
    }
}
