package com.nanxinda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.nanxinda.controller")
@EnableWebMvc
///类型：配置类注解
///位置：SpringMVC配置类定义上方
/// 开启SpringMVC多项辅助功能（包括开启将json数据转化为对象的功能）
public class SpringMVCConfig {
}
