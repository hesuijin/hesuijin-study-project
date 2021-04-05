package com.example.demo.concurrentUtilsClass.semaphoreDemo;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/5
 */
public class MyRunnable implements Runnable {

    //1.获得管理员对象，
    private Semaphore semaphore = new Semaphore(2);
    @Override
    public void run() {
        //2.获得通行证
        try {
            semaphore.acquire();
            //3.开始行驶
            System.out.println("获得了通行证开始行驶");
            System.out.println("时间:"+ new Date()+"  线程名称 :"+Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("归还通行证");
            //4.归还通行证
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
