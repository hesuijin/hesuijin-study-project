package com.example.demo.concurrentUtilsClass.semaphoreDemo;

/**
 * @Description:
 * 使用场景：  可以控制访问特定资源的线程数量。
 * @Author HeSuiJin
 * @Date 2021/4/5
 */
public class MySemaphoreDemo {
    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();

        for (int i = 0; i < 100; i++) {
            new Thread(mr).start();
        }
    }

}
