package com.nanxinda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//3.创建spring的配置文件，加载controller对应的bean
@Configuration
@ComponentScan("com.nanxinda.controller")
public class SpringMvcConfig {
}
