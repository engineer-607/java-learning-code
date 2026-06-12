package com.nanxinda.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class SpringMVCSupport extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        /// 当访问/pages/...时候不要走mvc，走pages目录下的内容
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        //addResourceHandler("/pages/**")表示资源处理器处理/pages下任意操作的时候
        //addResourceLocations("/pages/")访问/pages目录下的内容
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }
}
