package com.example.demo.thread.concurrencyLockThreadDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * 同步方法锁
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class TicketSynchronizedInstanceMethodDemo implements Runnable{

    private int ticket = 100;

    private AtomicInteger sellTicketCount = new AtomicInteger(0);

    /**
     * 执行卖票操作
     */
    @Override
    public void run() {
//      每个窗口卖票的操作
//      窗口 永远开启
        while (true) {
            sellTicket();
        }

    }

    //同步实例方法锁
    //隐含锁对象 谁调用该方法 就是该对象（this） 作为锁
    public synchronized void sellTicket(){
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
            System.out.println("现在一共卖了:" +  sellTicketCount.incrementAndGet());
        }
    }
}
