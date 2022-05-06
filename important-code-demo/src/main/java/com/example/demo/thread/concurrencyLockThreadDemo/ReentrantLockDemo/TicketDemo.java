package com.example.demo.thread.concurrencyLockThreadDemo.ReentrantLockDemo;


/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class TicketDemo {

    public static void main(String[] args) {
//        同步可重入锁
        TicketReentrantLockDemo ticket = new TicketReentrantLockDemo();

//     创建两个个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");

//     同时卖票
        t1.start();
        t2.start();
    }

}
