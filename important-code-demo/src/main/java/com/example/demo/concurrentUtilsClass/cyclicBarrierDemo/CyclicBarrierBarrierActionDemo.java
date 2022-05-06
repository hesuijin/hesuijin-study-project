package com.example.demo.concurrentUtilsClass.cyclicBarrierDemo;

import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2022/5/6
 */
public class CyclicBarrierBarrierActionDemo {

    //方式二：
    //当栏珊数等于0时,CyclicBarrier里面的主逻辑继续执行逻辑。
    //当CyclicBarrier里面的主逻辑执行完，才会继续执行线程用了cyclicBarrier.await()的逻辑。
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        // 当计数器为0时，立即执行
        @Override
        public void run() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("汇总线程任务合并。");
        }
    });

    public static void main(String[] args) {

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

        System.out.println("主线程结束");
    }
}
