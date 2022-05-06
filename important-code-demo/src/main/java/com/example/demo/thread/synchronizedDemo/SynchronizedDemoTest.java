package com.example.demo.thread.synchronizedDemo;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2022/5/1
 */
public class SynchronizedDemoTest {

    public static void main(String[] args) {
        //同步代码块（锁普通的String）
//        SynchronizedBlockDemoString ticket = new SynchronizedBlockDemoString();
        //同步代码块（锁intern的String）
        SynchronizedBlockDemoInternString ticket = new SynchronizedBlockDemoInternString();

//     创建两个个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");

//     同时卖票
        t1.start();
        t2.start();
    }

//    public static void main(String[] args) {
//
//        String a = new String("123");
//        String b = new String("4");
//
//        String s1 =a+b;
//        String s2 = s1.intern();
//        String s3 = "1234";
//
//        System.out.println(System.identityHashCode(a));
//        System.out.println(System.identityHashCode(b));
//
//        System.out.println(System.identityHashCode(s1));
//        System.out.println(System.identityHashCode(s2));
//        System.out.println(System.identityHashCode(s3));
//    }

//    public static void main(String[] args) {
//
////        String a = new String("123");
////        String b = new String("4");
//
//        String s1 =new String("123")+"4";
////        String b = new String("4");+"4";
//
//        String s4 ="123"+"4";
////        String b = new String("4");+"4";
//
//        String s2 = s1.intern();
//                String s3 = s4.intern();
//
////        String s3 = "1234";
////
////        StringBuilder
//
////        System.out.println(System.identityHashCode(b));
//
//        System.out.println(System.identityHashCode(s1));
//        System.out.println(System.identityHashCode(s2));
//        System.out.println(System.identityHashCode(s3));
//        System.out.println(System.identityHashCode(s4));
//    }

}
