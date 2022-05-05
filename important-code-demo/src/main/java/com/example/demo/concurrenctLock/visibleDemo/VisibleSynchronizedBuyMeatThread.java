package com.example.demo.concurrenctLock.visibleDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class VisibleSynchronizedBuyMeatThread extends Thread {

//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName() + " 原本购买肉价格是：" + VisibleSynchronizedDemo.money + ",但太贵了,因此先等肉降价!!!");
//        synchronized (VisibleSynchronizedDemo.objectLock) {
//            System.out.println("其他线程已经降低了肉的价格," + Thread.currentThread().getName() + "现在购买肉价格是：" + VisibleSynchronizedDemo.money);
//        }
//    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 原本购买肉价格是：" + VisibleSynchronizedDemo.money + ",但太贵了,因此先等肉降价!!!");
        while (true) {
            synchronized ("哈哈哈"){
            }
            if (VisibleSynchronizedDemo.money < 10) {
                break;
            }
        }
        System.out.println("其他线程已经降低了肉的价格," + Thread.currentThread().getName() + "现在购买肉价格是：" + VisibleSynchronizedDemo.money);
    }
}
