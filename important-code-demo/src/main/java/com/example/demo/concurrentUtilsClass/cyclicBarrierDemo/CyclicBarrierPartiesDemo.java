package com.example.demo.concurrentUtilsClass.cyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2022/5/6
 */
public class CyclicBarrierPartiesDemo {

    public static void main(String[] args) {
        //方式一：
        // 类似于countDownLatch  当栏珊数等于0时 继续执行逻辑。

        //cyclicBarrier.await() 与 countDownLatch.countDown()都是代表栏珊数减一。
        //但也有区别
        //某个线程cyclicBarrier.await() 后  栏珊数减一,但如果cyclicBarrier的栏珊数还是不等于0，则该线程需要在这里等着，
        //即是cyclicBarrier.await()代表本线程等着 栏珊数等于0

        //某个线程个countDownLatch.countDown()后  栏珊数减一,然后该线程继续执行，
        //而countDownLatch.await() 才是代表本线程等着 栏珊数等于0

//        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
         CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        //新建线程A
        new Thread(()->{
            try {
                System.out.println("线程A：" + Thread.currentThread().getName() + "执行任务。");
                System.out.println("线程A：到达屏障点");
                cyclicBarrier.await();
                System.out.println("线程A：退出屏障点");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        //新建线程B
        new Thread(()->{
            try {
                System.out.println("线程B：" + Thread.currentThread().getName() + "执行任务。");
                System.out.println("线程B：到达屏障点");
                cyclicBarrier.await();
                System.out.println("线程B：退出屏障点");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        try {
            System.out.println("主线程等待屏障点");
            cyclicBarrier.await();
            System.out.println("主线程继续执行逻辑");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束");
    }
}
