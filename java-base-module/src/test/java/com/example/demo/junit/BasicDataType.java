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

    @Test
    public void byteJunit() {
//        byte b ;
//        System.out.println(b);

        Byte bb;
//        System.out.println(bb);


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
        String stringNumber19 = "1234567891234567890";
        Long longTest19 = Long.parseLong(stringNumber19);
        System.out.println("19位转换成功："+longTest19);

        //20位
        try {
            String stringNumber20 = "12345678912345678900";
            Long longTest20 = Long.parseLong(stringNumber20);
            System.out.println("20位转换成功："+longTest20);
        }catch (NumberFormatException e){
            System.out.println("Long类型最多可以存储19位数字,否则产生异常："+e);
        }


    }

    @Test
    public void booleanJunit() {
        boolean test;
//        Boolean t = 0;

    }


}

