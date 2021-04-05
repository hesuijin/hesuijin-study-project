package com.example.demo.concurrentUtilsClass.countDownLatchDemo;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * 使用场景： 让某一条线程等待其他线程执行完毕之后再执行
 * @Author HeSuiJin
 * @Date 2021/4/5
 */
public class MyCountDownLatchDemo {
    public static void main(String[] args) {

        //1.创建CountDownLatch的对象，需要传递给四个线程。
        //在底层就定义了一个计数器，此时计数器的值就是2
        CountDownLatch countDownLatch = new CountDownLatch(2);
        //2.创建3个线程对象并开启他们。
        MotherThread motherThread = new MotherThread(countDownLatch);
        Thread thread = new Thread(motherThread);
        thread.start();

        ChildThread1 t1 = new ChildThread1(countDownLatch);
        Thread thread1 = new Thread(t1,"小何");

        ChildThread2 t2 = new ChildThread2(countDownLatch);
        Thread thread2 = new Thread(t2,"小张");

        thread1.start();
        thread2.start();
    }
}
