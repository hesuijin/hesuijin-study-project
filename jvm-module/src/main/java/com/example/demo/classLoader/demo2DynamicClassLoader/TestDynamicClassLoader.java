package com.example.demo.classLoader.demo2DynamicClassLoader;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class TestDynamicClassLoader {

    static {
        System.out.println("*************load TestDynamicLoad************");
    }

    public static void main(String[] args) {
        new ClassLoaderA();
        System.out.println("*************load test************");
        //B不会加载，除非这里执行 new B()
        ClassLoaderB b = null;
    }
}


