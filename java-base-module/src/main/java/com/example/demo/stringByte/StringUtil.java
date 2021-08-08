//package com.example.demo.stringByte;
//
//import java.io.UnsupportedEncodingException;
//
///**
// * @author Administrator
// */
//public class StringUtil {
//
//    private final static String CHARSET_NAME = "GBK";
//
//    /**
//     * 计算当前String字符串所占的总Byte长度
//     *
//     * @param args 要截取的字符串
//     * @return 返回值int型，字符串所占的字节长度，如果args为空或者“”则返回0
//     * @throws UnsupportedEncodingException
//     */
//    public static int getStringByteLenths(String args) throws UnsupportedEncodingException {
//        return args != null && !"".equals(args) ? args.getBytes(CHARSET_NAME).length : 0;
//    }
//
//    /**
//     * 按字节截取字符串 ，指定截取起始字节位置与截取字节长度
//     *
//     * @param orignal 要截取的字符串
//     * @return 截取后的字符串
//     */
//    public static String substringByte(String orignal, int start, int count) {
//
//        //如果目标字符串为空，则直接返回，不进入截取逻辑；
//        if (orignal == null || "".equals(orignal))
//            return "";
//
//        //截取Byte长度必须>0
//        if (count <= 0) {
//            return orignal;
//        }
//
//        //目标char Pull buff缓存区间；
//        StringBuffer buff = new StringBuffer();
//        try {
//            //截取字节起始字节位置大于目标String的Byte的length则返回空值
//            if (start >= getStringByteLenths(orignal)) {
//                return null;
//            }
//
//            int len = 0;
//            char c;
//
//            //遍历String的每一个Char字符，计算当前总长度
//            //如果到当前Char的的字节长度大于要截取的字符总长度，则跳出循环返回截取的字符串。
//            for (int i = 0; i < orignal.toCharArray().length; i++) {
//                c = orignal.charAt(i);
//                //当起始位置为0时候
//                if (start == 0) {
//                    len += String.valueOf(c).getBytes(CHARSET_NAME).length;
//                    if (len <= count) {
//                        buff.append(c);
//                    }
//                    else {
//                        break;
//                    }
//                }
//
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        //返回最终截取的字符结果;
//        //创建String对象，传入目标char Buff对象
//        return new String(buff);
//    }
//
//    /**
//     * 截取指定长度字符串
//     *
//     * @param orignal 要截取的目标字符串
//     * @param count   指定截取长度
//     * @return 返回截取后的字符串
//     */
//    public static String substringByte(String orignal, int count) {
//        return substringByte(orignal, 0, count);
//    }
//
//
//    public static void main(String[] args) {
//
//        //需求 截取字符串1 + 字符串2 相加为100字节（中文一个汉字两字节  英文一个字母一字节）的字符串  如果过长则先对字符串2进行截取
//        //情况1  如果字符串1本身超过100字节 则直接进行字符串1的截取即可
//        //情况2  如果字符串1+字符串2没有超过100字节  则不用管
//        //情况3   如果字符串1+字符串2超过100字节  则对字符串2进行截取
//        try {
//            //情况1
//            String string1 = "1234567891234567890哈哈哈";
//            String string2 = "我是String2";
//
//            //情况2
////            String string1 = "12345哈哈";
////            String string2 = "我是String";
//
//            //情况3
////            String string1 = "12345哈哈";
////            String string2 = "我是String999";
//
////            String string1 = "";
////            String string2 = "";
//
//            System.out.println("原始字符串1：" + string1);
//            System.out.println("原始字符串2：" + string2);
//            System.out.println("原始字符串字节长度1：" + string1.getBytes(CHARSET_NAME).length);
//            System.out.println("原始字符串字节长度2：" + string2.getBytes(CHARSET_NAME).length);
//
//            int string1Length = string1.getBytes(CHARSET_NAME).length;
//            int string2Length = string2.getBytes(CHARSET_NAME).length;
//
//            if (string1Length >= 20) {
//                System.out.println("原始字符串字节长度1：" + string1.getBytes(CHARSET_NAME).length);
//                string1 = StringUtil.substringByte(string1, 20);
//                string2 = "";
//                System.out.println("截取完的string1：" + string1);
//                System.out.println("截取完的string2：" + string2);
//            } else if (string1Length + string2Length <= 20) {
//                System.out.println("截取完的string1：" + string1);
//                System.out.println("截取完的string2：" + string2);
//            }else if (string1Length + string2Length >= 20){
//                int res = 20 - string1.getBytes(CHARSET_NAME).length;
//                string2 = StringUtil.substringByte(string2, res);
//                System.out.println("截取完的string1：" + string1);
//                System.out.println("截取完的string2：" + string2);
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//
//        }
//
//    }
//}