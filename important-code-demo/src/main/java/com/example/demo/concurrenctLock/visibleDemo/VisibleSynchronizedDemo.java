package com.example.demo.concurrenctLock.visibleDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class VisibleSynchronizedDemo {

    public static int money = 10;
    //设置objectLock共享变量 后续用于加锁
    public static Object objectLock = new Object();

    public static void main(String[] args) {
        VisibleSynchronizedBuyMeatThread meatThread = new VisibleSynchronizedBuyMeatThread();
        meatThread.setName("小何同学");
        meatThread.start();

        //使用synchronized进行加锁
        synchronized (VisibleSynchronizedDemo.objectLock) {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            VisibleSynchronizedDemo.money = 9;
            System.out.println("等了3秒后肉的价格降低为" + VisibleSynchronizedDemo.money + "块钱。");
        }

        //不要让主线程那么快结束 设置5秒的睡眠时间
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

