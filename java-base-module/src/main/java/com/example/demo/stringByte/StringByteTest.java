package com.example.demo.stringByte;

import java.io.UnsupportedEncodingException;

/**
 * @Description:测试String的字节数
 * @Author HeSuiJin
 * @Date 2021/8/8
 */
public class StringByteTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

//        byteDemo();

        //需求 截取字符串1 + 字符串2 相加为100字节（中文一个汉字两字节  英文一个字母一字节）的字符串  如果过长则先对字符串2进行截取
        //情况1  如果字符串1本身超过100字节 则直接进行字符串1的截取即可
        //情况2  如果字符串1+字符串2没有超过100字节  则不用管
        //情况3   如果字符串1+字符串2没有超过100字节  则对字符串2进行截取

//        byteStringDemo();


        String str = "hesuijin";
        int subSLength = 1;


    }

    private static void byteStringDemo() throws UnsupportedEncodingException {
        String string1 = "";
        String string2 = "";
//        System.out.println("测试abc".getBytes("GB2312").length);

    }





    /**
     * 一般情况下，采用ISO8859-1编码方式时，一个中文字符与一个英文字符一样只占1个字节；
     * 采用GB2312或GBK编码方式时，一个中文字符占2个字节；
     * 而采用UTF-8编码方式时，一个中文字符会占3个字节。
     *
     * @throws UnsupportedEncodingException
     */
    private static void byteDemo() throws UnsupportedEncodingException {
        // 运行结果：2
        System.out.println("测试".getBytes("ISO8859-1").length);
        // 运行结果：4
        System.out.println("测试".getBytes("GB2312").length);
        // 运行结果：4
        System.out.println("测试".getBytes("GBK").length);
        // 运行结果：6
        System.out.println("测试".getBytes("UTF-8").length);
    }


}