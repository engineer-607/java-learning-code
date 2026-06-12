package com.nanxinda.class_.Math_;

public class MathDetail {
    public static void main(String[] args) {
        //Math的常用方法
        //1.abs绝对值
        int abs = Math.abs(-9);
        System.out.println(abs);
        //2.pow求幂
        double pow = Math.pow(2,4);//2的4次方
        System.out.println(pow);
        //3.ceil向上取整，返回>=该参数的最小浮点数
        double ceil = Math.ceil(-3.001);
        System.out.println(ceil);
        //4.floor向下取整，返回<=该参数的最大浮点数
        double floor = Math.floor(-4.999);
        System.out.println(floor);
        //5.round 四舍五入
        long round = Math.round(5.51);
        System.out.println(round);//6
        //6.sqrt开方
        double sqrt = Math.sqrt(9.0);
        System.out.println(sqrt);
        //7.random求随机数
        // random 返回的是0-1之间的一个随机数
        //2-7之间的整数：Math.floor(Math.random*6+2))

    }
}
