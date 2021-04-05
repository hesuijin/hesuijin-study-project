package com.example.demo.classLoader.demo3JDKClassLoader;

import sun.misc.Launcher;

import java.net.URL;

/**
 *
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/5
 */
public class TestJDKClassLoader {

    public static void main(String[] args) {

        //引导类加载器 （C++语言）  调用（是怎么调用到这里的不用管） 该类Launcher的方法
        //获取 扩展类加载器  应用程序类加载器

//       引导类加载器（ C++ 语言 创建下面加载器） 为null  因为不是Java语言编写的
        System.out.println(String.class.getClassLoader());
//        扩展类加载器
//        sun.misc.Launcher$ExtClassLoader@5ca881b5    在Launcher下
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
//        应用程序类加载器
//        sun.misc.Launcher$AppClassLoader@18b4aac2  在Launcher下
        System.out.println(TestJDKClassLoader.class.getClassLoader());
        System.out.println();

//        appClassLoader的本类 AppClassLoader
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
//        appClassLoader的父类 ExtClassLoader
        ClassLoader extClassloader = appClassLoader.getParent();
//        extClassloader的父类  null
        ClassLoader bootstrapLoader = extClassloader.getParent();
        System.out.println("the appClassLoader : " + appClassLoader);
        System.out.println("the extClassloader : " + extClassloader);
        System.out.println("the bootstrapLoader : " + bootstrapLoader);


        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        //由于双亲委派 会把其他的路径也打印出来 但实际上只会使用下面这个
        //A:\MyProject\hesuijin-study-project\jvm-module\target\classes
        System.out.println(System.getProperty("java.class.path"));
    }
}
