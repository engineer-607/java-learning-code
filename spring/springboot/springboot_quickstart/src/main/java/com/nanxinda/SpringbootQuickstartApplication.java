package com.nanxinda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootQuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuickstartApplication.class, args);
    }

}
//springboot程序相较于spring程序只包含两个基础文件就可以正常运行
//1.pom.xml文件
//2.Application类
//注意事项：需要联网确保加载到框架结构
//SpringBoot简介
//设计目的是用来简化Spring应用的初始搭建以及开发过程
//优点：1.自动配置2.起步依赖(简化依赖配置)3.辅助功能(内置服务器...)

//SpringBoot起步依赖
//• starter
//    •SpringBoot中常见项目名称，定义了当前项目使的所有项目坐标，以达到减少依赖配置的目的
//•parent
//    •所有springBoot项目要继承的项目，定义了若干个坐标版本号（依赖管理，而非依赖），以达到减少依赖冲突的目的
//    •spring-boot-starter-parent（2.5.0）与 spring-boot-starter-parent（2.4.6）共计57处坐标版本不同
//•实际开发
//    •使用任意坐标时，仅书写GAV中的G和A，V由SpringBoot提供
//    •如发坐标错误，再指定version（要小心版本冲突）
//•辅助功能（tomcat、日志）
//•启动方式
//   •SpringBoot在创建项目时，采用jar的打包方式
//   •SpringBoot的引导类时项目的入口，运行main方法就可以启动项目
