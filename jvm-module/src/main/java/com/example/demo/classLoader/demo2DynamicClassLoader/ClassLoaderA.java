package com.example.demo.classLoader.demo2DynamicClassLoader;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class ClassLoaderA {
    static {
        System.out.println("*************load ClassLoaderA************");
    }

    public ClassLoaderA() {
        System.out.println("*************initial ClassLoaderA************");
    }
}
