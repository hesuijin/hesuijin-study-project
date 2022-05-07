package com.example.demo.thread.newThreadPool;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class ThreadPoolSubmitCallable implements Callable {

    @Override
    /*** 重写call方法，完成该线程执行的逻辑 */
    public Object call() {
        System.out.println(Thread.currentThread().getName()+"---->>使用一个实现Callable接口的类 重写方法");
        return "Success";
    }
}
