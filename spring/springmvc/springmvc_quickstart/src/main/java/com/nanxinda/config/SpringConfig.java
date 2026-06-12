package com.nanxinda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/// spring和springMVC都有各自的容器，需要加载各自的bean，springMVC控制的bean
/// 主要是在com.nanxinda.controller，而Spring控制的bean是业务bean（Service）、
/// 功能bean（DataSource）
/// Spring相关bean加载控制
/// 1.方式一：Spring加载的bean设定扫描范围为com.nanxinda，排除掉controller包内的bean
/// 2.Spring加载的bean设定扫描范围为精准范围，例如service包、dao包等
@Configuration
@ComponentScan({"com.nanxinda.service","com.nanxinda.dao"})//方式二
//@ComponentScan(
//        value = "com.nanxinda",
//        excludeFilters = @ComponentScan.Filter(
//                type = FilterType.ANNOTATION,
//                classes = Controller.class
//        )
//)
/// 类型：类注解
/// 属性：*excludeFilters:排除扫描路径中加载的bean，需要指定类别(type)与具体项(classes)(排除一些bean)
///      *includeFilters:加载指定的bean，需要指定类别(type)与具体项(classes)（指定追加一些bean）
public class SpringConfig {
}
