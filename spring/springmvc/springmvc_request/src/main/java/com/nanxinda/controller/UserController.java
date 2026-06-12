package com.nanxinda.controller;

import com.nanxinda.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
/// @RequestMapping补充
///类型：类注解、方法注解
///作用：设置当前控制器方法请求访问路径，如果设置在类上统一设置当前控制器方法请求方法访问路径前缀
public class UserController {
    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        System.out.println("user save...");
        return "{'module':user save}";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        System.out.println("user delete...");
        return "{'module':user delete}";
    }

    /// 传参
    //普通参数
    @RequestMapping("/commonParam")
    @ResponseBody
    public String commonParam(String name, int age) {
        System.out.println("普通参数传递name=>" + name);
        System.out.println("普通参数传递age=>" + age);
        return "{'module':common param}";
    }

    //普通参数：请求参数名与形参名不同
    @RequestMapping("/commonParamDifferentName")
    @ResponseBody
    public String commonParamDifferentName(@RequestParam("name") String userName, int age) {
        /// @RequestParam
        /// 类型：形参注解
        /// 位置：SpringMVC控制器方法形参定义前面
        /// 作用：绑定请求参数与处理器方法形参间的关系
        /// 参数：*required 是否为必传参数
        ///      *defaultValue 参数默认值
        System.out.println("普通参数传递 userName ==> " + userName);
        System.out.println("普通参数传递 age ==>" + age);
        return "{'module':'common param different name'}";
    }

    //POJ0参数
    @RequestMapping("/pojoParam")
    @ResponseBody
    //如果传入的参数和接受参数的POJO属性名一致，就会创建对象并将参数自动赋值给属性
    public String pojoParam(User user) {
        System.out.println("pojo参数传递 user ==> " + user);
        return "{'module':'pojo param'}";
    }
    //嵌套POJ0参数(传入的POJO中的属性也是POJO)
    @RequestMapping("/pojoContainPojoParam")
    @ResponseBody
    public String pojoContainPojoParam(User user) {
        System.out.println("pojo嵌套pojo参数传递user==>" + user);
        return "{'module':'pojo contain pojo param'}";
    }
    //数组参数
    @RequestMapping("/arrayParam")
    @ResponseBody
    public String arrayParam(String[] likes) {
        System.out.println("数组参数传递 likes ==>" + Arrays.toString(likes));
        return "{'module':'array param'}";
    }

    //集合参数
    @RequestMapping("/listParam")
    @ResponseBody
    public String listParam(@RequestParam List<String> likes) {
        System.out.println("集合参数传递 likes==>" + likes);
        return "{'module':'list param'}";
    }

    //集合参数：json格式
    @RequestMapping("/listParamForJson")
    @ResponseBody
    public String listParamForJson(@RequestBody List<String> likes) {
        ///@RequestBody
        /// 类型：形参注解
        /// 位置：SpringMVC控制器方法形参定义前面
        /// 作用：将请求中请求体所包含的数据传给请求参数，此注解处理器方法只能使用一次
        System.out.println("list common(json)参数传递1ist==>" + likes);
        return "{'module': list common for json param '}";
    }

    //P0Jo参数：json格式
    @RequestMapping("/pojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user) {
        System.out.println("pojo(json)参数傅递user==>" + user);
        return "{'module':'pojo for json param'}";
    }

    //集合参数：json格式
    @RequestMapping("/listPojoParamForJson")
    @ResponseBody
    public String listPojoParamForJson(@RequestBody List<User> list) {
        System.out.println("1ist pojo(json)参数传递 list==>" + list);
        return "{'module':'list pojo for json param'}";
    }

    /// @RequestParam和@RequestBody区别
    /// 区别
    /// * @RequestParam用于接收url地址传参，表单传参【application/x-www-form-urlencoded】
    /// * @RequestBody用于接收json数据【application/json】
    /// 应用
    /// * 后期开发中，发送json格式数据为主，@RequestBody应用较广
    /// * 如果发送非json格式数据，选用@RequestParam接收请求参数


}
