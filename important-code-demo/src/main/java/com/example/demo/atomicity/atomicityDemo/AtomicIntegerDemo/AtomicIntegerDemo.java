package com.example.demo.atomicity.atomicityDemo.AtomicIntegerDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        MyAtomicIntegerThreadDemo atom = new MyAtomicIntegerThreadDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(atom).start();
        }
    }
}
