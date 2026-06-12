package com.nanxinda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//2.定义controller
//2.1使用@Controller定义Bean
@Controller
/// 类型：类注解
/// 位置：SpringMVC控制器类定义上方
/// 作用：设定SpringMVC的核心控制器bean
public class UserController {
    //2.2设置当前操作的访问路径
    @RequestMapping("/save")
    /// 类型：方法注解
    /// 位置：SpringMVC控制器方法定义上方
    /// 作用：设置当前控制器方法请求路径
    /// 相关属性：value->默认访问路径
    //2.3设置当前操作的返回值类型
    @ResponseBody
    /// 方法注解
    /// 位置：SpringMVC控制器方法定义上方
    /// 设置当前控制器方法相应内容为当前返回值，无需解析
    public String save(){
        System.out.println("user save...");
        return "{'module':springmvc}";
    }
}
