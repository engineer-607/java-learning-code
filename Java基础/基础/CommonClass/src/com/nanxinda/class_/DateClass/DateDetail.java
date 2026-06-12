package com.nanxinda.class_.DateClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDetail {
    public static void main(String[] args) throws ParseException {
        Date d = new Date();//1）获取当前系统时间
        //2）将输出的格式进行转换的方式：
        //1.创建SimpleDateFormat对象，可以指定相应的格式
        //2.这里的格式使用的字母是规定
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss E");
        String format = simpleDateFormat.format(d);//将日期转换成指定格式的字符串
        System.out.println(format);
        //3）可以将一个格式化的String转换成对应的Date
        //*在把String->Date，使用的SimpleDateFormat对象的格式需要与所给的String格式一致，否则会抛出异常
        String s = "2026年02月04日 04:01:21 星期三";
        Date parse =  simpleDateFormat.parse(s);
        System.out.println(parse);

    }
}
