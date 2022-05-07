package com.example.demo.thread.concurrencyLockThreadDemo.ReentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition {

    private static boolean hasCigarette = false;
    private static boolean hasTakeout = false;
    private static final ReentrantLock reentrantLock = new ReentrantLock();

    // 等待烟的休息室（条件变量）
    static Condition waitCigaretteSet = reentrantLock.newCondition();
    // 等外卖的休息室（条件变量）
    static Condition waitTakeoutSet = reentrantLock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "是否持有锁:" +reentrantLock.isLocked());
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "是否持有锁:" +reentrantLock.isLocked());
            try {
                System.out.println("有烟没？"+ hasCigarette);
                if (!hasCigarette) {
                    System.out.println("没烟，先歇会！");
                    try {
                        // 此时张三进入到 等烟的休息室
                        //await() 把自己线程持有的锁释放 同时在这里等待
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("烟来咯, 可以开始干活了");
            } finally {
                System.out.println(Thread.currentThread().getName() + "逻辑结束 是否持有锁:" +reentrantLock.isLocked());
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName() + "逻辑结束 是否持有锁:" +reentrantLock.isLocked());
            }
        }, "张三").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "是否持有锁:" +reentrantLock.isLocked());
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "是否持有锁:" +reentrantLock.isLocked());
            try {
                System.out.println("外卖送到没？"+hasTakeout);
                if (!hasTakeout) {
                    System.out.println("没外卖，先歇会！");
                    try {
                        // 此时李四进入到 等外卖的休息室
                        //await() 把自己线程持有的锁释放 同时在这里等待
                        waitTakeoutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("外卖来咯, 可以开始干活了");
            } finally {
                System.out.println(Thread.currentThread().getName() + "逻辑结束 是否持有锁:" +reentrantLock.isLocked());
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName() + "逻辑结束 是否持有锁:" +reentrantLock.isLocked());
            }
        }, "李四").start();


        Thread.sleep(1000);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "等待锁");
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取锁");
            try {
                System.out.println("送烟的来咯~");
                hasCigarette = true;
                // 唤醒等烟的张三线程
                waitCigaretteSet.signal();
            } finally {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "释放锁");
                System.out.println(Thread.currentThread().getName() + "是否持有锁:" +reentrantLock.isLocked());
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName() + "是否持有锁:" +reentrantLock.isLocked());
            }
        }, "送烟的").start();

        Thread.sleep(1000);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "等待锁");
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取锁");
            try {
                System.out.println("送外卖的来咯~");
                hasTakeout = true;
                // 唤醒等外卖的李四线程
                waitTakeoutSet.signal();
            } finally {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "释放锁");
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName() + "是否持有锁:" +reentrantLock.isLocked());
            }
        }, "送外卖的").start();


        Thread.sleep(5000);
        System.out.println("主线程结束");

    }
}


