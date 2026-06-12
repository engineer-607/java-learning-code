package com.nanxinda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/// 表现层:主要做与前端做交互
@Configuration
@ComponentScan("com.nanxinda.controller") // 完美锁死表现层扫描，不碰持久层 config
@EnableWebMvc // 激活 Spring MVC 核心功能
public class SpringMvcConfig implements WebMvcConfigurer { // 💡 绝招：改为实现接口！

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 这样写，@EnableWebMvc 就会乖乖地来读取这四条放行规则
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }
}