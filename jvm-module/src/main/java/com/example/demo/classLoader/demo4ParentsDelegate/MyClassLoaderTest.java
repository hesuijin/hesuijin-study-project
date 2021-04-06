package com.example.demo.classLoader.demo4ParentsDelegate;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @Description:
 * 自定义 类加载器
 * @Author HeSuiJin
 * @Date 2021/4/6
 */
public class MyClassLoaderTest {

    public static void main(String args[]) throws Exception {
        //1：初始化自定义类加载器，会先初始化父类ClassLoader，
        //   其中会把自定义类加载器的父加载器默认设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        //2:com.myClassLoaderTest.jvm下创建 User类
        //3：D盘创建 test/com/myClassLoaderTest/jvm 几级目录，将User类的 .class文件  User.class丢入该目录

//        双亲委派
        //4：JDK的 classLoader.loadClass 里面有方法findClass （由于双亲委派 使用获取父加载器的该方法）
//        Class clazz = classLoader.loadClass("com.myClassLoaderTest.jvm.User");
        //5：获取该类存在的类加载器名称 （应用程序加载器） （由于双亲委派 先获取父加载器）
//        System.out.println(clazz.getClassLoader().getClass().getName());


        //注意 ：把2中的com.myClassLoaderTest.jvm下创建 User类  以及 .class文件删除 （防止双亲委派 先获取父加载器）

        //4：JDK的 classLoader.loadClass 里面有方法findClass  这时会调用到我们自定义实现的findClass方法
        Class clazz = classLoader.loadClass("com.myClassLoaderTest.jvm.User");
        //5：获取该类存在的类加载器名称 （自定义加载器）
        System.out.println(clazz.getClassLoader().getClass().getName());
    }


    static class MyClassLoader extends ClassLoader {
        private String classPath;


        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节 数组。
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

    }

}