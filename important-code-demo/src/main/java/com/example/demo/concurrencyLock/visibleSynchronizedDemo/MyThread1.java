package com.example.demo.concurrencyLock.visibleSynchronizedDemo;


/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class MyThread1 extends Thread{

    @Override
    public void run() {
        while(true){
            //该代码块在break无限循环
            //每次都重新获取锁
            //当money ！= 10时 打印并跳出
            synchronized (MoneySynchronized.lock){
                if(MoneySynchronized.money != 10){
                    System.out.println("辣条价格不是10块钱了");
                    break;
                }
            }
        }
    }
}
