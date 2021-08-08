package com.example.demo.stringByte;

import java.io.UnsupportedEncodingException;

/**
 * @Description:测试String的字节数
 * @Author HeSuiJin
 * @Date 2021/8/8
 */
public class StringByteTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        byteDemo();
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