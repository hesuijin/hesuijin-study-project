package com.example.demo.thread.daemonDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * 自定义守护线程
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
@Slf4j
public class MyThreadDaemon extends Thread{

    @Override
    public void run() {
        while(true){
            System.out.println(Thread.currentThread().getName() + ":" + "我是新线程啊");

            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                log.info("新线程睡眠异常："+e.getMessage(),e);
            }

        }
    }
}
