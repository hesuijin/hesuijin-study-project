package com.example.demo.concurrencyLock.visibleSynchronizedDemo;


/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MyThread2 extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (MoneySynchronized.lock) {
            MoneySynchronized.money = 9;
        }
    }
}
