package com.example.demo.classLoader.demo4ParentsDelegate;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @Description: 自定义 类加载器
 * @Author HeSuiJin
 * @Date 2021/4/6
 */
public class MyClassLoaderTest {

    public static void main(String args[]) throws Exception {
        //1：初始化自定义类加载器，会先初始化父类ClassLoader，
        //   其中会把自定义类加载器的父加载器默认设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        //2:com.example.demo.classLoader下创建 User类
        //3：D盘创建 test/com/example/demo/classLoader 几级目录，将User类的 .class文件  User.class丢入该目录

//        双亲委派
//        先查看自定义加载器是否进行过首次加载
//        自定义加载器没有进行首次加载  由于双亲委派 使用父加载器应用程序加载器进行查询并加载
//        应用程序加载器 可以在 A:\MyProject\hesuijin-study-project\jvm-module\target\classes  查询到User
//        因此使用应用加载器进行加载
//        Class clazz = classLoader.loadClass("com.example.demo.classLoader.User");
        //       获取该类的类加载器名称 （应用程序加载器）
//        System.out.println(clazz.getClassLoader().getClass().getName());

        //注意 ：把2中的com.example.demo.classLoader下创建 User类  以及 .class文件删除 （防止双亲委派 先获取父加载器）

        //使用自定义加载器进行加载
        //调用自定义加载器的findClass方法
        Class clazz = classLoader.loadClass("com.example.demo.classLoader.User");
        //4: 获取该类的类加载器名称 （自定义加载器）
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
//
//        注意测试打破双亲委派机制时  把注释打开
//        /**
//         *  最终代码
//         * 打破双亲委派机制  重写loadClass方法 去除双亲委派逻辑
//         * 针对核心类 还是使用委派机制
//         * @param name
//         * @param resolve
//         * @return
//         * @throws ClassNotFoundException
//         */
//        @Override
//        protected Class<?> loadClass(String name, boolean resolve)
//                throws ClassNotFoundException {
//            synchronized (getClassLoadingLock(name)) {
//                // First, check if the class has already been loaded
//                Class<?> c = findLoadedClass(name);
//                if (c == null) {
//                    long t0 = System.nanoTime();
//                    long t1 = System.nanoTime();
//
//                    //在进行Object的类（非自定义类）加载的时候  使用 父加载器进行加载
//                    if(!name.startsWith("com.example.demo")){
//                       c = this.getParent().loadClass(name);
//                    }else {
//                        c = this.findClass(name);
//                    }
//                    // this is the defining class loader; record the stats
//                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
//                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                    sun.misc.PerfCounter.getFindClasses().increment();
//
//                }
//                if (resolve) {
//                    resolveClass(c);
//                }
//                return c;
//            }
//        }
    }


}