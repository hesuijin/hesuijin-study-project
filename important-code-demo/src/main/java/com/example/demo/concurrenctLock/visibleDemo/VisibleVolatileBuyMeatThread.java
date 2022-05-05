package com.example.demo.concurrenctLock.visibleDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class VisibleVolatileBuyMeatThread extends Thread {

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " 原本购买肉价格是：" + VisibleVolatileDemo.money + ",但太贵了,因此先等肉降价!!!");
        while (true) {
//            System.out.println(Thread.currentThread().getName() + "在等待购买肉.");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (VisibleVolatileDemo.money < 10) {
                break;
            }
        }
        System.out.println("其他线程已经降低了肉的价格," + Thread.currentThread().getName() + "现在购买肉价格是：" + VisibleVolatileDemo.money);
    }
}
