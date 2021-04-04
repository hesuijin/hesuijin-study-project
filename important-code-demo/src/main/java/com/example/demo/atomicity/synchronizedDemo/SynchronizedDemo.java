package com.example.demo.atomicity.synchronizedDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class SynchronizedDemo {
    public static void main(String[] args) {

//        synchronized解决 :
//        1 ：先进入线程1
//        2 ：线程1进行循环  不断的 获取锁 并 释放锁
//                           若没有标志量则再次循环（此时没有标志量）
//        3 ：后进入线程2
//        4 ：线程2  尝试获取锁
//        5 ：线程2  获取锁成功  线程1等待
//        6 : 线程2  执行完逻辑 释放锁  （此时给予标志量）
//        7 ：线程1进行循环  获取锁  获取标志量 释放锁 结束


        MyThread1 t1 = new MyThread1();
        t1.setName("小何同学");
        t1.start();

        MyThread2 t2 = new MyThread2();
        t2.setName("小张同学");
        t2.start();
    }
}
