package com.example.demo.basicData;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/18
 */
public class FloatTest {

//    理论上计算机不能用二进制计算和表示1.1，同样也不能用二进制计算和表示0.1，
//    那为什么“2.0 - 1.1”会出现精度问题，而“1.0 - 0.1”却没问题？

//    public static void main(String[] args) {
//        double result = 2.0 - 1.1;
//        // 存在精度问题
//        System.out.println("2.0 - 1.1 = " + result);
//
//        result = 1.0 - 0.1;
//        // 不存在精度问题
//        System.out.println("1.0 - 0.1 = " + result);
//    }



    public static void main(String[] args) {

        CalBinary calBinary  = new CalBinary();
        //       0    011 111  111
        calBinary.calBinaryFloat(1.5f);

        calBinary.calBinaryFloat(2.0f);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //      0 011 111 111 111
        calBinary.calBinaryDouble(1.5);

        calBinary.calBinaryDouble(2.0);
        calBinary.calBinaryDouble(1.1);
        calBinary.calBinaryDouble(1.0);
        calBinary.calBinaryDouble(0.1);

//        2.0的二进制: 0 10000000000  0000000000000000000000000000000000000000000000000000
//        1.1的二进制: 0 01111111111  0001100110011001100110011001100110011001100110011010
//        1.0的二进制: 0 01111111111  0000000000000000000000000000000000000000000000000000
//        0.1的二进制: 0 01111111011  1001100110011001100110011001100110011001100110011010

        System.out.print("2.0 - 1.1 = ");
        calBinary.subtractBinary("10.0000000000000000000000000000000000000000000000000000",
                "01.0001100110011001100110011001100110011001100110011010");
    }
}
