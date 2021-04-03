package com.example.demo.thread.stateThreadDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */

// wait 与 notify都需要使用在 同步关键字：synchronized 中
// 否则会抛出异常IllegalMonitorStateException
// 抛出该异常表明某一线程已经试图等待对象的监视器，或者试图通知其他正在等待对象的监视器，然而本身没有指定的监视器的线程。

public class StateThreadDemo {

    public static Object obj = new Object();

//    下面是两个线程，一个等待线程，一个唤醒线程，都在一直执行，然后共用同一把锁

    public static void main(String[] args) {
//        演示waiting
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //注意需要争取锁 锁阻塞
                    synchronized (obj) {
                        try {
                            //计时等待 同时释放锁对象   5秒时间到，自动醒来
                            //obj.wait(5000);
                            System.out.println(Thread.currentThread().getName() + "=== 获取到锁对象，调用wait方法，开始进入waiting状态,释放锁对象");
                            //无限等待
                            obj.wait();
                            //这里无限等待后被唤醒后，并不一定立刻执行
                            // 由于synchronized (obj)   而此刻它已经不持有锁，所以她需要再次尝试去获取锁(很可能面临其它线程的竞争),如果没有则需要继续等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "=== 从waiting状 态醒来，获取到锁对象，继续执行了");
                    }
                }
            }
        }, "等待线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //每隔3秒 唤醒一次
                    try {
                        System.out.println(Thread.currentThread().getName() + "‐‐‐‐‐ 等待3秒钟");
                        //计时等待 L和没有L都是指毫秒
                        Thread.sleep(1500L);
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //注意需要争取锁  锁阻塞
                    synchronized (obj) {
                        System.out.println(Thread.currentThread().getName() + "‐‐‐‐‐ 获取到锁对象,调用notify方法，释放锁对象");
                        obj.notify();
                    }
                }
            }
        }, "唤醒线程").start();
    }
}
