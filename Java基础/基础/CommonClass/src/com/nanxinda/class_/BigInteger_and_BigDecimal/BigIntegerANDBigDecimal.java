package com.nanxinda.class_.BigInteger_and_BigDecimal;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerANDBigDecimal {
    public static void main(String[] args) {
        //BigInteger适合保存比较大的整型
        BigInteger bigInteger = new BigInteger("299999999999999999900");
        BigInteger bigInteger1 = new BigInteger("100");
        /*
        在对BigInteger进行加减乘除时，需要使用对应的方法，不能直接进行 + - * /
        可以创建一个要操作的BigInteger然后进行相应操作
         */
        BigInteger add = bigInteger.add(bigInteger1);
        System.out.println(add);//加
        BigInteger subtract = bigInteger.subtract(bigInteger1);
        System.out.println(subtract);//减
        BigInteger multiply = bigInteger.multiply(bigInteger1);
        System.out.println(multiply);//乘
        BigInteger divide = bigInteger.divide(bigInteger1);
        System.out.println(divide);//除
        //BigDecimal适合保存一个精度很高的数，double不够用
        BigDecimal bigDecimal = new BigDecimal("1998.983279823175");
        BigDecimal bigDecimal1 = new BigDecimal("1.1");
        //BigDecimal与BigInteger相同加减乘除要使用相应的方法
        //特别的是除法，如果除不尽为无限循环小数汇报出异常ArithmeticException
//        bigDecimal.divide(bigDecimal1);
        //处理方法，使用ROUND_CEILING,会保留原有浮点数的精度
        BigDecimal bigDecimal2 = bigDecimal.divide(bigDecimal1,BigDecimal.ROUND_CEILING);
        System.out.println(bigDecimal2);
    }
}
