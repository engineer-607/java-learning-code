package com.nanxinda.class_.pcakage_;

public class Integer01 {
    /*
    jdk5前是手动拆箱和装箱：装箱：基本类型-》包装类型，拆箱反之
    jak5以后为自动拆箱和装箱
     */
    public static void main(String[] args) {
        //jdk5以前
        //手动装箱:int->Integer
        int n1 = 100;
        Integer integer = new Integer(n1);
        Integer integer1 = Integer.valueOf(n1);
        //手动拆箱：Integer -> int
        int i = integer.intValue();

        //jdk5以后
        //自动拆箱 int ->Integer
        int n2 = 100;
        Integer integer2 = n2;//底层使用的仍然是Integer.valueOf(n2)
        //自动装箱 Integer -> int
        int n3 = integer2;//底层仍然使用intValue()
        //其他继承Number的包装类同理

        //包装类(Integer)->String
        Integer integer3 = 100;
        //方式一
        String str1 = integer3 +"";
        //方式二
        String str2 = integer3.toString();
        //方式三
        String str3 = String.valueOf(integer3);

        //String->包装类(Integer)
        String str4 = "12345";
        //方式一
        Integer integer4 = Integer.parseInt(str4);
        //方式二
        Integer integer5 = new Integer(str4);
    }
}
