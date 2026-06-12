package com.nanxinda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Rest风格
 * 1.按照REST风格访问资源时使用行为动作区分对资源进行了何种操作
 * http://localhost/users      查询全部用户信息 GET (查询)
 * http://localhost/users/1    查询指定用户信息 GET (查询)
 * http://localhost/users      添加用户信息    POST (新增/保存)
 * http://localhost/users      修改用户信息    PUT (修改/更新)
 * http://localhost/users/1    删除用户信息    DELETE (删除)
 * 2.根据REST风格对资源进行访问称为RESTful
 * 3.注意事项
 * 上述行为是约定方式，约定不是规范，可以打破，所以称REST风格，而不是REST规范
 * 描述模块的名称通常使用复数，也就是加s的格式描述，表示此类资源，而非单个资源，例如：users、books、accounts…..
 */
@Controller
public class UserController {
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    /// @RequestMapping设置method属性，可以定义http请求动作（GET/POST/PUT/DELETE）
    @ResponseBody
    public String save(){
        System.out.println("user save...");
        return "{'module':'user save}";
    }
    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getById(@PathVariable Integer id){
        /// @PathVariable
        /// 类型：形参注解
        /// 位置：SpringMVC控制器方法形参定义前面
        /// 作用：绑定路径参数与处理器方法形参间的关系，要求路径参数名与形参名一一对应
        System.out.println("user getById..."+id);
        return "{'module':'user getById}";
    }
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @ResponseBody
    public String getAll(){
        System.out.println("user getAll...");
        return "{'module':'user getAll}";
    }
    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id){
        System.out.println("user delete"+id);
        return "{'module':'user delete}";
    }
    @RequestMapping(value = "/users",method = RequestMethod.PUT)
    @ResponseBody
    public String update(){
        System.out.println("user update");
        return "{'module':'user update}";
    }
}
///@RequestBody @RequestParam @PathVariable
/// 区别
/// @RequestParam用于接收url地址传参或表单传参
/// @RequestBody用于接收json数据
/// @PathVariable用于接收路径参数，使用{参数名称}描述路径参数应用
/// 后期开发中，发送请求参数超过1个时，以json格式为主，@RequestBody应用较广
/// 如果发送非json格式数据，选用@RequestParam接收请求参数
/// 采用RESTful进行开发，当参数数量较少时，例如1个，可以采用@PathVariable接收请求路径变量，通常用于传递id值