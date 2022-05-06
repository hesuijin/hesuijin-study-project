package com.example.demo.thread.concurrencyLockThreadDemo.ticketSynchronized;


/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class TicketDemo {

    public static void main(String[] args) {
//    创建线程任务对象
//        Ticket ticket = new Ticket();

        //同步代码块
        TicketSynchronizedBlockDemo ticket = new TicketSynchronizedBlockDemo();
        //同步实例方法锁
//        TicketSynchronizedInstanceMethodDemo ticket = new TicketSynchronizedInstanceMethodDemo();
        //同步静态方法锁
//        TicketSynchronizedStaticMethodDemo ticket = new TicketSynchronizedStaticMethodDemo();

//     创建两个个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");

//     同时卖票
        t1.start();
        t2.start();
    }
}
