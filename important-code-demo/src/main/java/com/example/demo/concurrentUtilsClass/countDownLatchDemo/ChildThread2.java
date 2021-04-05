package com.example.demo.concurrentUtilsClass.countDownLatchDemo;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/5
 */
public class ChildThread2 implements Runnable{

        private CountDownLatch countDownLatch;
        public ChildThread2(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            //1.吃饺子
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "在吃第" + i + "个饺子");
            }
            //2.吃完说一声
            //每一次countDown方法的时候，就让计数器-1
            countDownLatch.countDown();
        }

}
