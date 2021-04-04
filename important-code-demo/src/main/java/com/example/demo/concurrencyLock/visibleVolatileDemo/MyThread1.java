package com.example.demo.concurrencyLock.visibleVolatileDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MyThread1 extends Thread{

    @Override
    public void run() {
        while(MoneyVolatile.money == 10){
        }
        System.out.println("辣条价格不是10块钱了");
    }
}
