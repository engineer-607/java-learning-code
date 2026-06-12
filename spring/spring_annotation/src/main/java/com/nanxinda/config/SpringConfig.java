package com.nanxinda.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@SuppressWarnings({"all"})
/// Spring3.0开启纯注解开发模式，使用Java类代替配置文件，开启了Spring快速开发的赛道
/// Java类代替Spring核心配置文件
/// @Configuration注解用于设定当前类为配置类
/// @ComponentScan注解用于设定扫描路径，此注解只能添加一次，多个数据请用数组格式
//@Configuration注解代表applicationContext.xml中的
//<?xml version="1.0" encoding="UTF-8"?>
//<beans xmlns="http://www.springframework.org/schema/beans"
//       xmlns:context="http://www.springframework.org/schema/context"
//       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//       xsi:schemaLocation="
//       http://www.springframework.org/schema/beans
//       http://www.springframework.org/schema/beans/spring-beans.xsd
//       http://www.springframework.org/schema/context
//       http://www.springframework.org/schema/context/spring-context.xsd
//">
//</beans>
@Configuration
//@ComponentScan注解代表
// <context:component-scan base-package="com.nanxinda"/>
@ComponentScan("com.nanxinda")
//@PropertySource注解等效于标签中
// <context:property-placeholder location="jdbc.properties"/>
//注意：不支持*.properties，也不支持*.classpath
//如果想加多个配置文件，用数组的形式{"jdbc.properties",...,}
@PropertySource("classpath:jdbc.properties")//classpath可以不加
//@Import将独立的配置类（第三方bean）加入核心配置
//使用@Import注解手动加入配置类到核心配置，此注解只能添加一次，多个数据使用数组格式
//补充：讲独立的配置类加入核心配置也可以使用扫描式（使用@ComponentScan注解扫描配置类所在的包
//加载对应的配置类信息），不过不推荐使用，因为隐藏性较强
@Import(JdbcConfig.class)
public class SpringConfig {

}
