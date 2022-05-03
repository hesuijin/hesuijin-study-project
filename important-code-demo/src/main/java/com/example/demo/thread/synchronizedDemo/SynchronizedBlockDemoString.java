package com.example.demo.thread.synchronizedDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * 同步代码块
 * @Author HeSuiJin
 * @Date 2021/4/3
 */
public class SynchronizedBlockDemoString implements Runnable  {

    private int ticket = 100;
    private AtomicInteger sellTicketCount = new AtomicInteger(0);

    /**
     * 执行卖票操作
     */
    @Override
    public void run() {

        //解析stringLock的引用  默认取常量池里面值
        //如果是 用我们正常 加号相连 实际上是用StringBuilder进行连接  并且New了一个对象
        String stringLock = "";
        String other = "你好啊";

//        stringLock 还是同一个对象了 可以锁住
//        if("窗口1".equals(Thread.currentThread().getName())){
//            stringLock = "hesuijin";
//            System.out.println(Thread.currentThread().getName()+"： stringLock的identityHashCode，判断是否同一个对象："+System.identityHashCode(stringLock));
//        }
//
//        if("窗口2".equals(Thread.currentThread().getName())){
//            stringLock = "hesuijin";
//            System.out.println(Thread.currentThread().getName()+"： stringLock的identityHashCode，判断是否同一个对象："+System.identityHashCode(stringLock));
//        }

//        stringLock 已经不是同一个对象了 无法锁住
        if("窗口1".equals(Thread.currentThread().getName())){
            stringLock = "hesuijin"+other;
            System.out.println(Thread.currentThread().getName()+"： stringLock的identityHashCode，判断是否同一个对象："+System.identityHashCode(stringLock));
        }

        if("窗口2".equals(Thread.currentThread().getName())){
            stringLock = "hesuijin"+other;
            System.out.println(Thread.currentThread().getName()+"： stringLock的identityHashCode，判断是否同一个对象："+System.identityHashCode(stringLock));
        }

//      每个窗口卖票的操作
//      窗口 永远开启
        while (true) {
            //同步代码块（锁对象）
            synchronized (stringLock) {
                if (ticket > 0) {
                    // 有票 可以卖
                    // 出票操作
                    // 使用sleep模拟一下出票时间
                    try {
                        //Sleep 的 1000（1秒）与 100（0.1秒）哪个重复的可能性比较大
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 获取当前线程对象的名字
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "正在卖:" + ticket--);
                    System.out.println("现在一共卖了:" +  sellTicketCount.incrementAndGet());
                }
            }
        }
    }
}
