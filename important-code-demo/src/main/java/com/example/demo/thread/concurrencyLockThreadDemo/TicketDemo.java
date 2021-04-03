package com.example.demo.thread.concurrencyLockThreadDemo;





import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class TicketDemo {

    public static void main(String[] args) {
//    创建线程任务对象
        Ticket ticket = new Ticket();

        //同步代码块
//        TicketSynchronizedBlockDemo ticket = new TicketSynchronizedBlockDemo();
        //同步方法锁
//        TicketSynchronizedMethodDemo ticket = new TicketSynchronizedMethodDemo();
        //同步锁
//        TicketLockDemo ticket = new TicketLockDemo();


//     创建三个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");
//     同时卖票
        t1.start();
        t2.start();
        t3.start();

        //使用线程池
        //暂时不用理会报错 后续会讲到线程池 现在先运行
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);//包含个线程对象
        newFixedThreadPool.submit(ticket);
        newFixedThreadPool.submit(ticket);
        newFixedThreadPool.submit(ticket);
        newFixedThreadPool.submit(ticket);
        newFixedThreadPool.submit(ticket);
        newFixedThreadPool.shutdown();

    }
}
