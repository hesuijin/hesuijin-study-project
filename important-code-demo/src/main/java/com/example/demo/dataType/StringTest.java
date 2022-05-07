package com.example.demo.dataType;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2022/5/3
 */
public class StringTest {

    public static void main(String[] args) {
        stringTestOne();
//        stringTestTwo();
    }

    private static void stringTestOne() {
        String stringOne = new String("hesuijin");
        String stringTwo = "hesuijin";
//        System.out.println("stringTestOne +++++++++++++++++++++++++++++++++++++++++" );
//        System.out.println("stringOne内存位置:" + System.identityHashCode(stringOne));
//        System.out.println("stringTwo内存位置:" + System.identityHashCode(stringTwo));
    }

//    private static void stringTestTwo() {
//        String stringOne = "hesuijin";
//        String stringTwo = new String("hesuijin");
//        System.out.println("stringTestTwo +++++++++++++++++++++++++++++++++++++++++" );
//        System.out.println("stringOne内存位置:" + System.identityHashCode(stringOne));
//        System.out.println("stringTwo内存位置:" + System.identityHashCode(stringTwo));
//    }

}
