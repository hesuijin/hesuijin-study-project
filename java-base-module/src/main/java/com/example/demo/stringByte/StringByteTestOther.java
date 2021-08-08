package com.example.demo.stringByte;

import java.io.UnsupportedEncodingException;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/8/8
 */
public class StringByteTestOther {


    public static String subStr(String str, int subSLength) throws UnsupportedEncodingException {

        if (str == null)
            return "";
        else {
            //截取字节数
            int tempSubLength = subSLength;
            String subStr = str.substring(0, str.length());

            //截取子串的字节长度
            int subStrByetsL = subStr.getBytes("GBK").length;

            //截取子串的字节长度
            //int subStrByetsL = subStr.getBytes().length;

            // 说明截取的字符串中包含有汉字
            while (subStrByetsL > tempSubLength) {
                int subSLengthTemp = --subSLength;

                subStr = str.substring(0, subSLengthTemp > str.length() ? str.length() : subSLengthTemp);

                subStrByetsL = subStr.getBytes("GBK").length;

                //subStrByetsL = subStr.getBytes().length;

            }
            return subStr;

        }
    }

    /**
     * 判断是否是一个中文汉字
     *
     * @param c 字符
     * @return true表示是中文汉字，false表示是英文字母
     * @throws UnsupportedEncodingException 使用了JAVA不支持的编码格式
     */
    public static boolean isChineseChar(char c)
            throws UnsupportedEncodingException {
        // 如果字节数大于1，是汉字
        // 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
        return String.valueOf(c).getBytes("GBK").length > 1;
    }

    private static  String  subStringByByte(String str, int len) throws UnsupportedEncodingException {
        String result = null;
        if (str != null) {
            byte[] a = str.getBytes("Unicode");
            if (a.length <= len) {
                result = str;
            } else if (len > 0) {
                result = new String(a, 0, len);
                int length = result.length();
                if (str.charAt(length - 1) != result.charAt(length - 1)) {
                    if (length < 2) {
                        result = null;
                    } else {
                        result = result.substring(0, length - 1);
                    }
                }
            }
        }
        return result;
    }
}
