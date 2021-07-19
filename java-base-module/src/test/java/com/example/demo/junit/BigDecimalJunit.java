package com.example.demo.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @Description: BigDecimal单元测试
 * @Author HeSuiJin
 * @Date 2021/7/19
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BigDecimalJunit {

//    https://blog.csdn.net/qq_35868412/article/details/89029288

    /**
     * bigDecimal的创建 int double long string
     */
    @Test
    public  void createBigDecimal(){
        BigDecimal bigDecimalInt = new BigDecimal(0);
        BigDecimal bigDecimalLong = new BigDecimal(0L);

        //不推荐（double）
        BigDecimal bigDecimalDouble = new BigDecimal(1.1);
        //推荐
        BigDecimal bigDecimalString = new BigDecimal("1.1");

        //为什么不推荐使用double类型的bigDecimal (先不管)
        //为什么不推荐使用double类型的bigDecimal (先不管)
        //为什么不推荐使用double类型的bigDecimal (先不管)

        //1.100000000000000088817841970012523233890533447265625
        System.out.println("bigDecimalDouble:"+bigDecimalDouble);
        //1.1
        System.out.println("bigDecimalString:"+bigDecimalString);

        //当double必须用作BigDecimal的源时，可以使用以下两种方式(实际上的内部实现是一样的)
        // 1：使用BigDecimal的静态方法valueOf
        // 2：使用Double.toString(double)转成String，然后使用String构造方法

        BigDecimal valueOfDouble = BigDecimal.valueOf(1.1);
        BigDecimal toStringDouble = new BigDecimal(Double.toString(1.1));
        System.out.println("valueOfDouble:"+valueOfDouble);
        System.out.println("toStringDouble"+toStringDouble);
    }

    /**
     * BigDecimal的使用  加减乘除
     */
    @Test
    public void BigDecimalUse(){
        BigDecimal first = new BigDecimal("100");
        BigDecimal second = new BigDecimal("2");
        System.out.println("first+second:"+first.add(second));
        System.out.println("first-second:"+first.subtract(second));
        System.out.println("first*second:"+first.multiply(second));
        System.out.println("first/second:"+first.divide(second));

        BigDecimal firstOther = new BigDecimal("100");
        BigDecimal secondOther = new BigDecimal("3");
        try {
            System.out.println("firstOther/secondOther:"+firstOther.divide(secondOther));
        }catch (ArithmeticException e){
            log.error("BigDecimal无法除尽异常",e);
        }

        //如果进行除法运算的时候，结果不能整除，有余数，这个时候会报java.lang.ArithmeticException:
        //要避免这个错误产生，在进行除法运算的时候，针对可能出现的小数产生的计算，有两种做法

        //1：divide(BigDecimal，舍入模式)
        //2：divide(BigDecimal，scale(保留小数点后几位小数)，舍入模式)

        //ROUND_HALF_UP 我们最常见的四舍五入
        System.out.println("firstOther/secondOther  无法除尽的数值最后会按照舍入规则 保留成整数:"+firstOther.divide(secondOther,BigDecimal.ROUND_HALF_UP));
        System.out.println("firstOther/secondOther  无法除尽的数值最后会按照舍入规则 保留成小数:"+firstOther.divide(secondOther,5,BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 核心方法 Scale
     */
    @Test
    public void BigDecimalScale(){
        BigDecimal bigDecimal = new BigDecimal("1.555");
        //四舍五入 保留两位小数
        bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);
    }
}
