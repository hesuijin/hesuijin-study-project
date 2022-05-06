package com.example.demo.concurrentUtilsClass.cyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: 循环栏珊
 * @Author HeSuiJin
 * @Date 2022/5/6
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
//        CyclicBarrier指定了计数器为2，
//        线程A和线程B都分别执行了2次cyclicBarrier.await();
//        这样导致的效果就是：
//            每个线程分阶段完成任务，等所有线程都完成了第1步，
//            然后才能接着执行第2步，等所有线程都完成了第2步，
//            才能执行第3步。

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        //新建线程A
        new Thread(()->{
            try {
                System.out.println("线程A：" + Thread.currentThread().getName() + "执行第1步。");
                cyclicBarrier.await();
                System.out.println("线程A：" + Thread.currentThread().getName() + "执行第2步。");
                cyclicBarrier.await();
                System.out.println("线程A：" + Thread.currentThread().getName() + "执行第3步。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        //新建线程B
        new Thread(()->{
            try {
                System.out.println("线程B：" + Thread.currentThread().getName() + "执行第1步。");
                cyclicBarrier.await();
                System.out.println("线程B：" + Thread.currentThread().getName() + "执行第2步。");
                cyclicBarrier.await();
                System.out.println("线程B：" + Thread.currentThread().getName() + "执行第3步。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("主线程结束");
    }
}
