package com.example.demo.thread.newThreadPool;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        //尝试创建一个标准的线程池
        threadPoolExecutorDemo();

//       核心线程数量为1 ， 最大线程池数量为3, 任务容器的容量为1 ,
//     空闲线程（即大于核心线程数的线程）的最大存在时间为20s   然后运行5个任务
        Integer RejectedPolicy = 3;
        switch (RejectedPolicy) {
            case 1:
                //丢弃任务并抛出RejectedExecutionException异常。是默认的策略。
                abortPolicyDemo();
                break;
            case 2:
                // 丢弃任务，但是不抛出异常 这是不推荐的做法。
                discardPolicyDemo();
                break;
            case 3:
                //  抛弃队列中等待最久的任务 然后把当前任务加入队列中
                discardOldestPolicyDemo();
                break;
            case 4:
                // 丢调用任务的run()方法绕过线程池直接执行  (谁调用 则使用谁的线程)
                callerRunsPolicyDemo();
                break;
            default:
                break;
        }

    }

    /**
     * 一个标准的线程池
     */
    private static void threadPoolExecutorDemo() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        //使用一个实现Runnable接口的类重写方法
        threadPoolExecutor.submit(new ThreadPoolExecutorRunnable());
        //使用内部类重写方法
        threadPoolExecutor.submit(()->{
            System.out.println(Thread.currentThread().getName() + "---->> 使用匿名内部类 重写方法");
        });
        threadPoolExecutor.shutdown();
    }


    /**
     * 丢弃任务并抛出RejectedExecutionException异常。是默认的策略。
     */
    private static void abortPolicyDemo() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3, 20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // 提交5个任务，而该线程池最多可以处理4个任务，当我们使用AbortPolicy这个任务处理策略的时候，就会抛出异常
        for (int x = 1; x <= 5; x++) {
            final int y = x;
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "---->> 执行了任务"+y);
            });
        }
    }

    /**
     * 丢弃任务，但是不抛出异常 这是不推荐的做法。
     */
    private static void discardPolicyDemo() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3, 20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        // 提交5个任务，而该线程池最多可以处理4个任务，当我们使用DiscardPolicy这个任务处理策略的时候，控制台不会报错
        for (int x = 1; x <= 5; x++) {
            final int y = x;
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "---->> 执行了任务"+y);
            });
        }
    }

    /**
     * 抛弃队列中等待最久的任务 然后把当前任务加入队列中
     */
    private static void discardOldestPolicyDemo() {
        ThreadPoolExecutor threadPoolExecutor;
        threadPoolExecutor = new ThreadPoolExecutor(1, 3, 20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        // 提交5个任务

        //由于任务2在线程池中等待时间最长，因此任务2被丢弃。 1 2 3 4 5
        // 1    小于等于进入核心线程数 直接执行
        // 2    阻塞队列未满  进入阻塞队列
        // 3 4  阻塞队列已满  最大线程数未来满  进入最大线程数  直接创建线程运行
        // 5    此时任务数大于等于  最大线程数+阻塞队列容量 ，则实行线程池自定义的拒绝策略。  把队列中的2挤出来
        // 当线程数  大于核心线程数的线程  超过空闲时间  都没有获取到队列 则关闭这些空闲线程

        for (int x = 1; x <= 5; x++) {
            // 定义一个变量，来指定指定当前执行的任务;这个变量需要被final修饰
            final int y = x;
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "---->> 执行了任务" + y);
            });
        }
    }

    /**
     * 调用任务的run()方法绕过线程池直接执行。(谁调用 则使用谁的线程)
     */
    private static void callerRunsPolicyDemo() {
        ThreadPoolExecutor threadPoolExecutor;
        threadPoolExecutor = new ThreadPoolExecutor(1, 3, 20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        // 提交5个任务
        for (int x = 1; x <= 5; x++) {
            final int y = x;
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "---->> 执行了任务"+y);
            });
        }
    }
}
