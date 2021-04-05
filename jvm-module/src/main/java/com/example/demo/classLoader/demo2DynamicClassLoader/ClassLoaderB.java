package com.example.demo.classLoader.demo2DynamicClassLoader;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class ClassLoaderB {
    static {
        System.out.println("*************load ClassLoaderB************");
    }

    public ClassLoaderB() {
        System.out.println("*************initial ClassLoaderB************");
    }
}
