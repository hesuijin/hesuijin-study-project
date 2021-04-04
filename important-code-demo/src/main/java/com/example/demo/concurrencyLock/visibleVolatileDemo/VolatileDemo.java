package com.example.demo.concurrencyLock.visibleVolatileDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class VolatileDemo {
    public static void main(String[] args) {

//        1 ：堆内存是唯一的，每一个线程都有自己的线程栈。
//        2 ：每一个线程在使用堆里面变量的时候，都会先拷贝一份到变量 到线程的线程栈中。
//        3 ：在线程中，每一次使用变量都是从  线程的线程栈 中获取的
//
//        当A线程修改了共享数据时，B线程没有及时获取到最新的值，如果还在使用原先的值，就会出现问题
//        小何同学虽然知道一开始辣条价格是10块钱，但是当辣条的价格变化的时候，无法知道最新的余额。

//        Volatile关键字 : 强制线程每次在使用的时候，都会看一下共享区域最新的值
        MyThread1 t1 = new MyThread1();
        t1.setName("小何同学");
        t1.start();

        MyThread2 t2 = new MyThread2();
        t2.setName("小张同学");
        t2.start();
    }
}
