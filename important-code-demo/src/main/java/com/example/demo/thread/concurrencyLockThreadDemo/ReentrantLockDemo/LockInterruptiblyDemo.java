package com.example.demo.thread.concurrencyLockThreadDemo.ReentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2022/5/6
 */
public class LockInterruptiblyDemo {

    private  static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        //主线程开始上锁
        reentrantLock.lock();

        Thread interruptiblyThread = new Thread(() -> {
            lockInterruptibly();
        }, "小何同学");
        interruptiblyThread.start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("开始中断小何同学的线程");
        // 锁 被中断
        interruptiblyThread.interrupt();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "是否持有锁:" + reentrantLock.isLocked());
        //主线程开始解锁
        reentrantLock.unlock();
        System.out.println(Thread.currentThread().getName() + "主线程结束,并且释放锁！");
    }

    public static void lockInterruptibly() {

        try {
            //可以被中断的锁
            System.out.println(Thread.currentThread().getName() + "try get lock ");
            //如果线程被直接抛出 会直接抛出异常
            reentrantLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " get lock success");
        } catch (InterruptedException e) {
            System.out.println(e);
            System.out.println(Thread.currentThread().getName() + " get lock Fail");
            System.out.println(Thread.currentThread().getName() + "抛异常 结束逻辑");
            return;
        } finally {
            //注意 需要如果获取到锁  最后一定要释放锁
            //如果线程没有获取到锁就直接释放  会报IllegalMonitorStateException异常
            System.out.println(Thread.currentThread().getName() + "是否持有锁:" + reentrantLock.isLocked());
            if (reentrantLock.isLocked()) {
                reentrantLock.unlock();
            }
        }

//        System.out.println(Thread.currentThread().getName() + "try get lock ");
//        //如果线程被中断 还是会一直在这里等待
//        reentrantLock.lock();
//        System.out.println(Thread.currentThread().getName() + " get lock success");
//        System.out.println(Thread.currentThread().getName() + "获取锁 结束逻辑");
//        reentrantLock.unlock();
    }
}


