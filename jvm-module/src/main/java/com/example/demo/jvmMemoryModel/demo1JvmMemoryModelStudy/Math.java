package com.example.demo.jvmMemoryModel.demo1JvmMemoryModelStudy;

import com.example.demo.jvmMemoryModel.User;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/4
 */
public class Math {

    public static final int initData = 666;
    public static User user = new User();

    //一个方法对应一块栈帧内存区域
    public int compute() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    //一个方法对应一块栈帧内存区域
    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}
