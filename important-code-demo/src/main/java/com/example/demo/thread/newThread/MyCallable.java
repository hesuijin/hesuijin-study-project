package com.example.demo.thread.newThread;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class MyCallable implements Callable {

    @Override
    /*** 重写call方法，完成该线程执行的逻辑 */
    public Object call() throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return null;
    }
}
