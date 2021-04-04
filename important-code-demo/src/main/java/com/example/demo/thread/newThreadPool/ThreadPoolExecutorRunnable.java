package com.example.demo.thread.newThreadPool;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class ThreadPoolExecutorRunnable implements Runnable {

    @Override
    /*** 重写run方法，完成该线程执行的逻辑 */
    public void run() {
        System.out.println(Thread.currentThread().getName()+"---->>使用一个实现Runnable接口的类 重写方法");
    }
}
