package com.example.demo.concurrenctLock.visibleDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class VisibleVolatileDemo {

    //添加 volatile 关键字
    //volatile关键字 : 强制线程每次在使用的时候，都会看一下共享区域最新的值

    public static  int money = 10;
//    public static int money = 10;

    public static void main(String[] args) {
        VisibleVolatileBuyMeatThread meatThread = new VisibleVolatileBuyMeatThread();
        meatThread.setName("小何同学");
        meatThread.start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VisibleVolatileDemo.money = 9;
        System.out.println("等了1秒后肉的价格降低为"+ VisibleVolatileDemo.money +"块钱。");

        //不要让主线程那么快结束 设置5秒的睡眠时间
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

