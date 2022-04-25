package com.example.demo.thread.newThread;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * 创建线程的方式总共有四种方法，继承Thread, 实现Runnable接口,实现Callable接口,使用Executor框架来创建线程池（单独讲解）
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class NewThreadDemo {

    //      java虚拟机启动一个进程，主线程main在main()调用时候被创建。
    public static void main(String[] args) {
        //继承Thread
//        extendThread();
        //实现Runable
//        implRunnable();
        //实现Callable
        implCallable();
    }

    private static void extendThread(){
//      随着调用myThread的对象的 start方法，另外一个新的线程也启动了，
//      创建新的线程对象
        MyThread myThread = new MyThread("新的线程！继承Thread新线程");
//      开始新的线程对象
        myThread.start();

//      在主方法中执行for循环
        for (int i = 0; i < 1000; i++) {
            System.out.println("旧的线程！ Main函数旧线程" + i);
        }
    }

    private static void implRunnable(){
        //创建自定义类对象 线程任务对象
        MyRunnable myRunnable = new MyRunnable();
        //创建新的线程对象
        Thread thread = new Thread(myRunnable, "新的线程！实现Runnable新线程");
        //开始新的线程对象
        thread.start();

        //main函数主线程
        for (int i = 0; i < 1000; i++) {
            System.out.println("旧的线程！ Main函数旧线程" + i);
        }
    }

    private static void implCallable()  {
        //创建自定义类对象 线程任务对象
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> oneTask = new FutureTask<Integer>(myCallable);
        //创建新的线程对象
        Thread thread = new Thread(oneTask, "新的线程！实现Callable新线程");
//       开始新的线程对象
        thread.start();

        try {
            System.out.println("打印Callable的响应结果" + JSONObject.toJSONString(oneTask.get()));
            //中断异常
        } catch (InterruptedException e) {
            e.printStackTrace();
            //当试图检索一个任务的结果时抛出异常
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //main函数主线程
        for (int i = 0; i < 1000; i++) {
            System.out.println("旧的线程！ Main函数旧线程" + i);
        }
    }
}
