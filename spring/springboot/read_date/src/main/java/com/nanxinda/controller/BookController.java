package com.nanxinda.controller;

import com.nanxinda.domain.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    //1.使用Value注解读取数据(属性名引用方式：${一级属性名.二级属性名})
    @Value("${lesson}")
    private String lesson;
    @Value("${server.port}")
    private int port;
    @Value("${enterprise.subject[0]}")
    private String subject_00;

    //2.通过代理模式实现接口Environment实现代理实现类AbstractEnvironment
    //该实现类才是真正存放文件内容的地方，这里真正的依赖注入注入的是实现类
    @Autowired
    private Environment environment;
    //3.Enterprise类读取资源文件内容，spring将其加入IoC容器中，通过注入
    //成员Enterprise类获取数据
    @Autowired
    private Enterprise enterprise;

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id){
        System.out.println(lesson);
        System.out.println(port);
        System.out.println(subject_00);
        System.out.println("----------------");
        System.out.println(environment.getProperty("enterprise.age"));
        System.out.println("----------------");
        System.out.println(enterprise.getAge());
        return "hello,springboot!";
    }
}
