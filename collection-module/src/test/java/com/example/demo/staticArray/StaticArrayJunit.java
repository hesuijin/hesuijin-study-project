package com.example.demo.staticArray;

import com.example.demo.dataCreataTion.CreateStaticArray;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 1: 静态数组 使用 length方法获取容量 该容量为初始化时的容量
 * 2：静态数组  有 基本类型int数组 与 包装类Integer数组 （其他类似）  但注意 只有String数组
 * 3：静态数组  各种静态数组没有给对应下标赋值时 int对应的值为0  Integer对应的值为null  String对应的值为null
 * 4：静态数组 有基本类型与包装类数组  动态数组只有包装类
 * 5：静态数组 有可能会数组越界  动态数组会动态扩容
 *
 * @Description: 静态数组测试
 * @Author HeSuiJin
 * @Date 2021/7/8
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class StaticArrayJunit {

    @Autowired
    private CreateStaticArray createStaticArray;

    @Test
    public void methodArrayIntJunit() {
        int[] arrayInt = createStaticArray.methodArrayInt();

        //打印arrayInt的长度
        int arrayIntLength = arrayInt.length;
        System.out.println("arrayIntLength：" + arrayIntLength);

        for (int i = 0; i < arrayInt.length; i++) {
            System.out.println("打印arrayInt：" + arrayInt[i]);
        }

        int[] arrayIntClone = arrayInt.clone();
        for (int i = 0; i < arrayIntClone.length; i++) {
            System.out.println("打印arrayIntClone：" + arrayIntClone[i]);
        }
    }

    @Test
    public void methodArrayIntegerJunit() {
        Integer[] arrayInteger = createStaticArray.methodArrayInteger();

        //打印arrayInteger的长度
        int arrayIntLength = arrayInteger.length;
        System.out.println("arrayIntegerLength：" + arrayIntLength);

        for (int i = 0; i < arrayInteger.length; i++) {
            System.out.println("打印arrayInteger：" + arrayInteger[i]);
        }

        Integer[] arrayIntegerClone = arrayInteger.clone();
        for (int i = 0; i < arrayIntegerClone.length; i++) {
            System.out.println("打印arrayIntegerClone：" + arrayIntegerClone[i]);
        }
    }

    @Test
    public void methodArrayStringJunit() {
        String[] arrayString = createStaticArray.methodArrayString();

        //打印arrayStringLength的长度
        int arrayStringLength = arrayString.length;
        System.out.println("arrayStringLength：" + arrayStringLength);

        for (int i = 0; i < arrayString.length; i++) {
            System.out.println("打印arrayString：" + arrayString[i]);
        }

        String[] arrayStringClone = arrayString.clone();
        for (int i = 0; i < arrayString.length; i++) {
            System.out.println("打印arrayStringClone：" + arrayStringClone[i]);
        }
    }

    /**
     * 对比 静态数组 和 动态数组
     */
    @Test
    public void methodArrayAndListJunit() {

        List<Integer> integerListNew = new ArrayList();
//        List<int> intListNew = new ArrayList<>();

        Integer[] arrayInteger = createStaticArray.methodArrayInteger();
        int[] arrayInt = createStaticArray.methodArrayInt();

        List<Integer> integerList = Arrays.asList(arrayInteger);
//        List<int> intList = Arrays.asList(arrayInt);

        String[] arrayString = createStaticArray.methodArrayString();
        try {
            String indexOutOfString = arrayString[5];
        }catch (ArrayIndexOutOfBoundsException e){
//            "打印异常"
//            e.getMessage() 为 5
//            e为Throwable (java.lang.ArrayIndexOutOfBoundsException: 5 具体到哪一行报错)
            log.error("静态数组越界  打印异常: "+e.getMessage(),e);
        }

        //静态数组转动态数组
        List<String> stringList = Arrays.asList(arrayString);

//        动态数组的类型应该为 ArrayList
//        stringList的类型为 Arrays$ArrayList 代表它是由静态数组转成动态数组的  实际上无法使用动态数组的方法
//        java.lang.UnsupportedOperationException
        try {
            stringList.add("我可以不能使用 动态数组的方式的哈");
        }catch (UnsupportedOperationException e){
            log.error("静态数组转换为动态数组后无法使用方法 打印异常: "+e.getMessage(),e);
        }


        System.out.println(stringList);
    }

    /**
     * String 与 String类型的静态数组 互转
     */
    @Test
    public void methodArrayStringAndStringJunit() {
        String[] strings = "1,2,3".split(",");
        for (int i = 0; i < strings.length; i++) {
            System.out.println("打印转换的strings：" + strings[i]);
        }

        String transformString = "";
        for (int i = 0; i < strings.length; i++) {
            if (transformString == null || "".equals(transformString)) {
                transformString = strings[i];
            } else {
                transformString = transformString + "," + strings[i];
            }
        }
        System.out.println("打印转换的transformString：" + transformString);
    }

}
