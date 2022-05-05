package com.example.demo.concurrenctLock.atomicityDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MyAtomThreadVolatile implements Runnable{
    //送冰淇淋的数量
    private volatile int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            count++;
            System.out.println("volatile 已经送了" + count + "个冰淇淋");
        }
    }
}
