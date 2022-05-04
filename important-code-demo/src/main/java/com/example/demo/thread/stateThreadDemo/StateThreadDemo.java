package com.example.demo.thread.stateThreadDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/3
 */

// wait 与 notify都需要使用在 同步关键字：synchronized 中
// 一个线程wait（等待） 的过程中先把锁释放掉
// 一个线程被notify（唤醒）后不一定立刻可以获取锁
// 否则会抛出异常IllegalMonitorStateException
// 抛出该异常
//        1:表明某一线程试图等待对象的监视器，
//        2:表明某一线试图通知其他正在等待对象的监视器，然而本身没有指定的监视器的线程。
public class StateThreadDemo {

    public static void main(String[] args) {
//        threadState();
        deadLock();
    }


    /**
     * 线程状态测试
     */
    private static void threadState() {
        Object obj = new Object();
        //    下面是两个线程，一个等待线程，一个唤醒线程，都在一直执行，然后共用同一把锁
        //        演示waiting
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //注意需要争取锁 锁阻塞
                    synchronized (obj) {
                        try {
                            //计时等待同时释放锁对象（其他地方可以获取到锁）
                            //5秒时间到，自动醒来
                            //obj.wait(5000);
                            System.out.println(Thread.currentThread().getName() + "=== 获取到锁对象，调用wait方法，开始进入waiting状态,释放锁对象");
                            //无限等待同时释放锁对象（其他地方可以获取到锁）
                            obj.wait();
                            //这里无限等待后被唤醒后，并不一定立刻执行
                            // 由于synchronized (obj)   而此刻它已经不持有锁，
                            //所以她需要再次尝试去获取锁(很可能面临其它线程的竞争)
                            //如果没有则需要继续等待
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

    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    /**
     * 死锁测试
     */
    private static void deadLock() {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();
    }
}
