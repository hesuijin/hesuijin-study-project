package com.example.demo.concurrenctLock.atomicityDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MyAtomThreadSynchronized implements Runnable{
    //送冰淇淋的数量
    private  int count = 0;
    private Object lock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (lock) {
                count++;
                System.out.println("synchronized 已经送了" + count + "个冰淇淋");
            }
        }
    }
}
