package com.nanxinda.annotaion;

import java.util.ArrayList;
import java.util.List;

public class SuppressWarnings_ {
    //抑制编译器警告
    //在{""}中可以写入需要抑制的警告信息
    //常用的抑制警告词
//    @SuppressWarnings("unchecked")          // 抑制未经检查的转换警告
//    @SuppressWarnings("deprecation")        // 抑制使用过时API的警告
//    @SuppressWarnings("rawtypes")           // 抑制原始类型警告
//    @SuppressWarnings("unused")             // 抑制未使用代码的警告
//    @SuppressWarnings("serial")             // 抑制缺少serialVersionUID的警告
    @SuppressWarnings({"rawtypes","unused","unchecked"})//放置在main方法上，抑制范围为main方法
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("jack");
        list.add("tom");
        list.add("mary");
        int i;
        System.out.println(list.get(1));
        //SuppressWarning的源码
        /*
        @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface SuppressWarnings {

        String[] value();//需要传入字符串数组
    }

         */

    }
}
