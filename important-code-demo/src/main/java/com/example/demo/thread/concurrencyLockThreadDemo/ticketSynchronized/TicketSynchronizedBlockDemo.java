package com.example.demo.thread.concurrencyLockThreadDemo.ticketSynchronized;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * 同步代码块
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class TicketSynchronizedBlockDemo implements Runnable  {

    private int ticket = 100;
    private AtomicInteger sellTicketCount = new AtomicInteger(0);
    Object lock = new Object();

    /**
     * 执行卖票操作
     */
    @Override
    public void run() {
//      每个窗口卖票的操作
//      窗口 永远开启
        while (true) {
            //同步代码块（锁对象）
            synchronized (lock) {
                if (ticket > 0) {
                    // 有票 可以卖
                    // 出票操作
                    // 使用sleep模拟一下出票时间
                    try {
                        //Sleep 的 1000（1秒）与 100（0.1秒）哪个重复的可能性比较大
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 获取当前线程对象的名字
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "正在卖:" + ticket--);
                    System.out.println("现在一共卖了:" +  sellTicketCount.incrementAndGet());
                }
            }
        }
    }
}
