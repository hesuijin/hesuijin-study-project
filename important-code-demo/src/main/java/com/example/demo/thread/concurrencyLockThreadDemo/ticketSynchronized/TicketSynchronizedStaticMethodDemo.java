package com.example.demo.thread.concurrencyLockThreadDemo.ticketSynchronized;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * 同步静态方法锁
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class TicketSynchronizedStaticMethodDemo implements Runnable{

    private static int ticket = 100;
    private static AtomicInteger sellTicketCount = new AtomicInteger(0);

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

    //同步静态方法锁
    //给当前类方法加锁，会作用于类的所有对象实例
    public static synchronized void sellTicket(){
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
