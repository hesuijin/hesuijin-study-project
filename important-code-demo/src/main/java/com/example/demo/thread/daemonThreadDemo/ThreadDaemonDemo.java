package com.example.demo.thread.daemonThreadDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * 三个角色 Java虚拟机  主线线程（非守护线程）   普通线程（守护/非守护）
 *
 * 关系：
 * 当运行的线程都是守护线程时，Java虚拟机将退出   （即是主线程执行完  普通线程是守护线程时  JVM会退出）
 *
 * 当主线程退出时
 *                1：非守护线程还在运行
 *                2：守护线程还也会退出
 * 垃圾回收线程就是守护线程
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
@Slf4j
public class ThreadDaemonDemo {
    public static void main(String[] args) {


        MyThreadDaemon myThreadDaemon = new MyThreadDaemon();
        myThreadDaemon.setName("张羽");

        //设置主线程为刘备
        Thread.currentThread().setName("刘备");

        // 用户自定义的线程标记为守护线程（用户线程）
        myThreadDaemon.setDaemon(true);
//        myThreadDaemon.setDaemon(false);

        myThreadDaemon.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                log.info("旧线程睡眠异常："+e.getMessage(),e);
            }
        }
    }
}
