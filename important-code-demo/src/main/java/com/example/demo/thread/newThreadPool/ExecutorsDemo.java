package com.example.demo.thread.newThreadPool;

import com.example.demo.thread.newThread.MyRunnable;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class ExecutorsDemo {

    public static void main(String[] args) throws InterruptedException {
        //默认线程池
//        cachedThreadPoolDemo();
        //可以指定线程数量的线程池
//        newFixedThreadPoolDemo();
    }

    private static void cachedThreadPoolDemo() {
        //1,创建一个默认的线程池对象.池子中默认是空的.默认最多可以容纳int类型的最大值.
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Executors --- 可以帮助我们创建线程池对象
        //ExecutorService --- 可以帮助我们控制线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "在执行了");
        });
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "在执行了");
        });
        executorService.shutdown();
    }

    private static void newFixedThreadPoolDemo() {
        //参数不是初始值而是最大值
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executorService;
        //大小为0
        System.out.println(pool.getPoolSize());
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "在执行了");
        });
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "在执行了");
        });
        //大小为2
        System.out.println(pool.getPoolSize());
        executorService.shutdown();
    }
}
