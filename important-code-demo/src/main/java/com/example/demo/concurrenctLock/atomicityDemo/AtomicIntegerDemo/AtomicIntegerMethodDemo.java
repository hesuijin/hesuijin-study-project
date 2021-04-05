package com.example.demo.concurrenctLock.atomicityDemo.AtomicIntegerDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * AtomicInteger 的常见用法
 *
 *
 * java从JDK1.5开始提供了java.util.concurrent.atomic包(简称Atomic包)，
 * 这个包中的原子操作类提供了一种用法简单，性能高效，线程安全地更新一个变量的方式
 * * Atomic包里一共提供了13个类，属于4种类型的原子更新方式
 * 分别是原子更新基本类型、原子更新数组、原子更新引用和原子更新属性(字段)
 * 使用原子的方式更新基本类型
 *
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class AtomicIntegerMethodDemo {

//    AtomicBoolean： 原子更新布尔类型
//    AtomicInteger： 原子更新整型
//    AtomicLong：	原子更新长整型

    public static void main(String[] args) {
        //获取值
        AtomicInteger ac1 = new AtomicInteger(10);
        System.out.println(ac1.get());

        //以原子方式将当前值加1，注意，这里返回的是自增前的值。
        AtomicInteger ac2 = new AtomicInteger(10);
        int getAndIncrement = ac2.getAndIncrement();
        System.out.println(getAndIncrement);
        System.out.println(ac2.get());

        //以原子方式将当前值加1，注意，这里返回的是自增后的值。
        AtomicInteger ac3 = new AtomicInteger(10);
        int incrementAndGet = ac3.incrementAndGet();
        System.out.println(incrementAndGet);
        System.out.println(ac3.get());

        //以原子方式将参数与对象中的值相加，并返回结果。
        AtomicInteger ac4 = new AtomicInteger(10);
        int i = ac4.addAndGet(20);
        System.out.println(i);
        System.out.println(ac4.get());

        //以原子方式设置为newValue的值，并返回旧值。
        AtomicInteger ac5 = new AtomicInteger(100);
        int andSet = ac5.getAndSet(20);
        System.out.println(andSet);
        System.out.println(ac5.get());
    }

}
