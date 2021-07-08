package com.example.demo.dataCreataTion;

import org.springframework.stereotype.Component;

/**
 * @Description: 创建静态数组
 * @Author HeSuiJin
 * @Date 2021/7/8
 */
@Component
public class CreateStaticArray {

    public int[] methodArrayInt() {
//        静态初始化
//        int[] arrayInt = new int[]{1, 2, 3};

//        动态初始化
        int[] arrayInt = new int[5];
        arrayInt[0] = 1;
        arrayInt[1] = 2;
        arrayInt[2] = 3;
        return arrayInt;
    }

    public Integer[] methodArrayInteger() {
//        静态初始化
//        Integer[] arrayInteger = new Integer[]{1,2,3};

//        动态初始化
        Integer[] arrayInteger = new Integer[5];
        arrayInteger[0] = 1;
        arrayInteger[1] = 2;
        arrayInteger[2] = 3;
        return arrayInteger;
    }

    public String[] methodArrayString() {
//        静态初始化
//        String[] arrayString = new String[]{"你","好","啊"};

//        动态初始化
        String[] arrayString = new String[5];
        arrayString[0] = "你";
        arrayString[1] = "好";
        arrayString[2] = "啊";
        return arrayString;
    }
}
