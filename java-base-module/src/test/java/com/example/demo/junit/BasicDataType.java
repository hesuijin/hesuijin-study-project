package com.example.demo.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/17
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BasicDataType {


    /**
     * 整型
     */
    @Test
    public void basicIntJunit() {
        byte bMin = -128;
        byte bMax = 127;
        System.out.println("byte的最小值："+bMin);
        System.out.println("byte的最大值："+bMax);

        short sMin = - 32768;
        short sMax = 32767;
        System.out.println("short的最小值："+sMin);
        System.out.println("short的最大值："+sMax);

        int iMin = -2147483648;
        int iMax = 2147483647;
        System.out.println("int的最小值："+iMin);
        System.out.println("int的最大值："+iMax);

//        9 223 372 034 854 800 000 l
//        9 223 372 034 854 700 000 l
        long lMin = -9223372036854700000l;
        long lMax =  9223372036854700000l;
        System.out.println("byte的最小值："+lMin);
        System.out.println("byte的最大值："+lMax);
    }


    /**
     * 浮点型
     */
    @Test
    public void basicFloatJunit() {
        float float1 = 1.1f;
        float float2 = 2.0f;

        System.out.println(float2 - float1);

        //
        System.out.println(2.00 - 1.10);

        float fMin = -2147483648111f;
        float fMax = 214748364711f;
        System.out.println("int的最小值："+fMin);
        System.out.println("int的最大值："+fMax);

//        2.00和1.10都要在计算机里转换进行二进制存储，这就涉及到数据精度，出现这个现象的原因正是浮点型数据的精度问题。先了解下float、double的基本知识:
//        1. float和double是java的基本类型，用于浮点数表示，在java中float占4个字节32位，double占8个字节64位，一般比较适合用于工程测量计算中，其在内存里的存储结构如下：
//

//        9 223 372 034 854 800 000 l
//        9 223 372 034 854 700 000 l
//        long lMin = -9223372036854700000l;
//        long lMax =  9223372036854700000l;
//        System.out.println("byte的最小值："+lMin);
//        System.out.println("byte的最大值："+lMax);
    }


    @Test
    public void booleanJunit() {
        boolean test;
//        Boolean t = 0;

    }


    /**
     * Long范围 -9.22 33720368548 e+18  到 9.22 33720368548 e+18 减1
     */
    @Test
    public void longJunit() {
        //18位
        String stringNumber18 = "123456789123456789";
        Long longTest18 = Long.parseLong(stringNumber18);
        System.out.println("18位转换成功："+longTest18);

        //19位
        String stringNumber19True = "1234567891234567890";
        Long longTest19True = Long.parseLong(stringNumber19True);
        System.out.println("19位转换成功："+longTest19True);

        //20位（一定报错）
        try {
            String stringNumber19False = "9223372036854800000";
            Long longTest19False = Long.parseLong(stringNumber19False);
            System.out.println("19位转换临界值 失败："+longTest19False);
        }catch (NumberFormatException e){
            System.out.println("9223372036854800000（9.22 33720368548 e+18 ） 异常："+e);
        }

        //需要把尾数 800000 修改为  700000
        String stringNumberLast = "9223372036854700000";
        Long longTest19Last = Long.parseLong(stringNumberLast);
        System.out.println("19位转换临界值 成功："+longTest19Last);


        //20位（一定报错）
        try {
            String stringNumber20 = "12345678912345678900";
            Long longTest20 = Long.parseLong(stringNumber20);
            System.out.println("20位转换成功："+longTest20);
        }catch (NumberFormatException e){
            System.out.println("Long类型最多可以存储19位数字,否则产生异常："+e);
        }
    }

}

