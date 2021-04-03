package com.example.demo.thread.newThread;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class MyRunnable implements Runnable{

    @Override
    /*** 重写run方法，完成该线程执行的逻辑 */
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
