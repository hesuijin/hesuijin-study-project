package com.example.demo.concurrenctLock.atomicityDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class AtomicityDemo {

    public static void main(String[] args) {
        //Volatile 原子性测试  (仅有可见性 没有原子性)
        atomThreadVolatile();
        //Synchronized 原子性测试
//        atomThreadSychronized();
    }

    private static void atomThreadVolatile(){
        //里面的count不准确  就是因为volatile没有原子性  导致count++的时候
        MyAtomThreadVolatile atom = new MyAtomThreadVolatile();
        for (int i = 0; i < 10; i++) {
            new Thread(atom).start();
        }
    }

    private static void atomThreadSychronized(){
        MyAtomThreadSynchronized atom = new MyAtomThreadSynchronized();
        for (int i = 0; i < 10; i++) {
            new Thread(atom).start();
        }
    }

}
