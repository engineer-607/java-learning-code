package com.nanxinda.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/book")
public class BookController {
    @RequestMapping("/save")
    @ResponseBody
    public String save(){
        System.out.println("book save...");
        return "{'module':book save}";
    }

    @RequestMapping("/dateParam")
    @ResponseBody
    public String dataParam(Date date,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")Date date2
    ) {
        /// @DateTimeFormat
        /// 类型：形参注解
        /// 位置：SpringMVC控制器方法形参前面
        /// 作用：设定日期时间型数据格式
        System.out.println("参数传递 date ==>" + date);
        System.out.println("参数传递 date1(yyyy-MM-dd)==> "+date1);
        System.out.println("参数传递 date2(yyyy/MM/dd HH:mm:ss)==>"+date2);
        return "{'module':'data param'}";
    }
}
///Converter接口
//public interface Converter {
//    Object convert(Object var1, Class var2, Object var3);
//}
//可以将请求参数的年龄数据进行转化(String->Integer)
//也可以进行日期格式化转化(String->Date)
//每种转化都有默认的转化，如果发现没有按照默认的转化规则进行需要添加在servlet配置上
//添加@EnableWebMvc，这个注解可以根据匹配对应的类型转化器
