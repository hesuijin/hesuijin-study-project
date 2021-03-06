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
            //1,从共享数据中读取数据到本线程栈中.
            //2,修改本线程栈中变量副本的值
            //3,会把本线程栈中变量副本的值赋值给共享数据.
            synchronized (lock) {
                count++;
                System.out.println("Synchronized 已经送了" + count + "个冰淇淋");
            }
        }
    }
}
