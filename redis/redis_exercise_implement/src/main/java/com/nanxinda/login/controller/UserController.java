package com.nanxinda.login.controller;

import com.nanxinda.login.dto.LoginFormDTO;
import com.nanxinda.login.dto.Result;
import com.nanxinda.login.dto.SendDTO;
import com.nanxinda.login.dto.UserDTO;
import com.nanxinda.login.service.UserService;
import com.nanxinda.login.utils.UserHolder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//NOTE Session&Cookie:
// cookie:浏览器保存的一小部分数据、Session是保存的服务器的一份用户会话数据
// 浏览器第一次访问服务器
//         ↓
// 服务器创建一个 Session，例如 id = abc123
//         ↓
// 服务器把 Session 数据保存在自己内存里
//         ↓
// 服务器返回 Cookie：JSESSIONID=abc123
//         ↓
// 浏览器保存这个 Cookie
//         ↓
// 以后每次请求自动带上 JSESSIONID=abc123
//         ↓
// 服务器根据 abc123 找到对应的 Session 数据

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    /**
     * 发送验证码
     * @param dto 传入参数：登录方式，手机号/邮箱号
     * @return 发送是否成功
     */
    @PostMapping("/code")
    //NOTE 如果前端是以query（请求头）/form表单形式传参，那么后端以对象的形式接受参数不需要注解
    // @RequestBody
    // 如果前端发送的形式是json形式（请求体），才需要RequestBody
    public Result sendCode(SendDTO dto){
        return service.sendCode(dto.type,dto.sender);
    }

    /**
     * 登录功能
     * @param loginFormDTO 包括登录方式，手机号/邮箱号，验证码
     * @return 执行结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginFormDTO){
        return service.login(loginFormDTO);
    }

    @GetMapping("/me")
    public Result me(){
        //获取当前用户的信息
        UserDTO userDTO = UserHolder.getUser();
        return Result.ok(userDTO);
    }
}
