package com.nanxinda.class_.DateClass;

import java.util.Calendar;

public class CalendarDetail {
    public static void main(String[] args) {
         //Calendar是一个抽象类，并且构造器是private
        //可以通过getInstance()来获取实例
        Calendar c = Calendar.getInstance();//创建日历类对象
        System.out.println("c=" +c);
        //获取日历对象的某个日历字段
        System.out.println("年："+c.get(Calendar.YEAR));
        //Calendar返回月时候，是按照0开始编号
        System.out.println("月："+(c.get(Calendar.MONTH)+1));
        System.out.println(c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"
        +c.get(Calendar.DAY_OF_MONTH));
        /*
        Calendar存在的问题：
        1）可变性：像日期和时间这样的类应该是不可变的
        2）偏移性：Date中的年份是从1990开始的，而月份从0开始
        3）格式化：格式化只对Date有用，Calendar则不行
        4）此外，它们也不是线程安全的；不能处理闰秒（每隔2天，多出1s）
         */
    }
}
