package com.nanxinda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.nanxinda.controller", "com.nanxinda.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
//@Configuration
//@ComponentScan("com.itheima.controller")
//@EnableWebMvc
//public class SpringMvcConfig implements WebMvcConfigurer{
//    @Autowired
//    private ProjectInterceptor projectInterceptor;
//
//    public void addInterceptors(InterceptorRegistry registry) {
//           registry.addInterceptor(projectInterceptor).addPathPatterns("/books", "/books/*");
//    }
//}
//可有用这种简化版的开发，将拦截器过滤器写道主配置类中
//但是这种侵入式，代码耦合度较高，规范的是分开写但是不是写在的扫描config这个包，而是用@Import这个注解
//如果用扫config包的形式的话会去扫spring持久化层的配置类，就会把持久层容器中的bean扫进来会造成混乱
