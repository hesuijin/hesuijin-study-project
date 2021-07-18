package com.example.demo.basicData;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/18
 */
public class CalBinary {

    /**
     * float 类型转 二进制
     * @param f
     */
    public void calBinaryFloat(float f){
        System.out.print(f + "的二进制: ");
        int value = Float.floatToIntBits(f);
        // 计算float的31位二进制编码，此处注意java默认是Big endian
        for (int i = 31; i >= 0; i--) {
            int v = (int) ((value >> i) & (0x1));
            System.out.print(v);
        }
        System.out.println();
    }

    /**
     * double 类型转 二进制
     * @param d
     */
    public void calBinaryDouble(double d){
        System.out.print(d + "的二进制: ");
        long value = Double.doubleToRawLongBits(d);
        // 计算double的64位二进制编码，此处注意java默认是Big endian
        for (int i = 63; i >= 0; i--) {
            int v = (int) ((value >> i) & (0x1));
            System.out.print(v);
        }
        System.out.println();
    }



    /**
     * 计算二进制结果
     */
    public void  subtractBinary(String subtrahend, String minuend) {
        List<Character> result = new ArrayList<Character>();
        int tmp = 0;
        for (int i = subtrahend.length() - 1; i >= 0; i--) {
            char subtrahendChar = subtrahend.charAt(i);
            if (subtrahendChar == '.') {
                result.add('.');
                continue;
            }
            char minuendChar = minuend.charAt(i);
            if (tmp == 1) {
                minuendChar += 1;
                tmp = 0;
            }
            if (subtrahendChar < minuendChar) {
                subtrahendChar += 2;
                tmp = 1;
            }
            result.add((char) (subtrahendChar - minuendChar));
        }
        for (int i = result.size() - 1; i >= 0; i--) {
            char value = result.get(i);
            if (value == '.') {
                System.out.print(value);
                continue;
            }
            System.out.print((int) result.get(i));
        }
        System.out.println();
    }
}
