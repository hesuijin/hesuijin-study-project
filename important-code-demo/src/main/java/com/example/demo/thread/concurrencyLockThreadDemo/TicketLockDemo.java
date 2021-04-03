package com.example.demo.thread.concurrencyLockThreadDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:lock 锁
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class TicketLockDemo implements Runnable {

    private int ticket = 100;

    //    Lock锁也称同步锁，加锁与释放锁简单化了
    Lock lock = new ReentrantLock();

    /**
     * 执行卖票操作
     */
    @Override
    public void run() {
//      每个窗口卖票的操作
//      窗口 永远开启
        while (true) {
            lock.lock();
            try {
                if (ticket > 0) {
                    // 有票 可以卖
                    // 出票操作
                    // 使用sleep模拟一下出票时间
                    try {
                        //1000（1秒）与 100（0.1秒）哪个重复的可能性比较大
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 获取当前线程对象的名字
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "正在卖:" + ticket--);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}
