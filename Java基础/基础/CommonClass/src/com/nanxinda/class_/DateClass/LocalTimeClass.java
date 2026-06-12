package com.nanxinda.class_.DateClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class LocalTimeClass {
    public static void main(String[] args) {
        /*
        第三代日期常见方法：
        1）LocalDate（日期/年月日）、LocalTime（时间/时分秒）、LocalDateTime（时间/年月日时分秒）
        JDK8加入
         */
        //1.使用now()返回表示当前日期时间的对象
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        System.out.println("年=" + ldt.getYear());
        System.out.println("月=" + ldt.getMonth());
        System.out.println("月=" + ldt.getMonthValue());
        System.out.println("日=" + ldt.getDayOfMonth());
        System.out.println("时=" + ldt.getHour());
        System.out.println("分=" + ldt.getMinute());
        System.out.println("秒=" + ldt.getSecond());
        //2.使用DateTimeFormatter对象来进行格式化
        //创建DateTimeFormatter对象
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH小时mm分钟ss秒");
        String format = dateTimeFormatter.format(ldt);
        System.out.println("格式化的日期="+format);
        //3.Instant时间戳
        //类似于Date
        //提供一系列和Date类转换的方式
        //Instant->Date
        //Date date = Date.from(instant);
        //Date->Instant
        //Instant instant = date.toInstant();
        Instant now = Instant.now();
        System.out.println(now);
        Date date = Date.from(now);
        Instant now1 = date.toInstant();
        //第三类日期更多方法
        LocalDateTime localDateTime = ldt.plusDays(999);
        System.out.println("999天之后是"+dateTimeFormatter.format(localDateTime));
        LocalDateTime localDateTime1 = ldt.minusMinutes(99999);
        System.out.println("99999分钟之前是"+dateTimeFormatter.format(localDateTime1));
    }
}
