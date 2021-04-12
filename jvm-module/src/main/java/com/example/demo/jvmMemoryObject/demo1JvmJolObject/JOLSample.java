package com.example.demo.jvmMemoryObject.demo1JvmJolObject;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/10
 */
public class JOLSample {

    public static void main(String[] args) {
        ClassLayout layoutObject = ClassLayout.parseInstance(new Object());
        System.out.println(layoutObject.toPrintable());
        System.out.println();
        ClassLayout layoutInt = ClassLayout.parseInstance(new int[]{});
        System.out.println(layoutInt.toPrintable());
        System.out.println();
        ClassLayout layoutSampleObject = ClassLayout.parseInstance(new SampleObject());
        System.out.println(layoutSampleObject.toPrintable());
    }

    // ‐XX:+UseCompressedOops 默认开启的压缩所有指针
    // ‐XX:+UseCompressedClassPointers 默认开启的压缩对象头里的类型指针Klass Pointer
    //  Oops : Ordinary Object Pointers
    public static class SampleObject {
        // 8B mark word
        // 4B Klass Pointer

        //4B
        // 如果关闭压缩‐XX:‐UseCompressedClassPointers或‐XX:‐UseCompressedOops，则占用8B
        int id;

        //4B
        // 如果关闭压缩‐XX:‐UseCompressedOops，则占用8B
        String name;

        //1B
        byte b;

        //4B
        // 如果关闭压缩‐XX:‐UseCompressedOops，则占用8B
        Object o;

    }

}
