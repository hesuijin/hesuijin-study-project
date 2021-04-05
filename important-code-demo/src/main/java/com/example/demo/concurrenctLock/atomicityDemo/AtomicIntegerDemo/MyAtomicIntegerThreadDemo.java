package com.example.demo.concurrenctLock.atomicityDemo.AtomicIntegerDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * AtomicInteger原理 : 自旋锁  + CAS 算法
 * CAS算法：
 * 	有3个操作数（内存值V， 旧的预期值A，要修改的值B）
 * 	当旧的预期值A == 内存值   此时修改成功，将V改为B
 * 	当旧的预期值A！=内存值   此时修改失败，不做任何操作
 * 	并重新获取现在的最新值（这个重新获取的动作就是自旋）
 *
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MyAtomicIntegerThreadDemo implements Runnable{

    AtomicInteger ac = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int count = ac.incrementAndGet();
            System.out.println("AtomicInteger 已经送了" + count + "个冰淇淋");
        }
    }
}
