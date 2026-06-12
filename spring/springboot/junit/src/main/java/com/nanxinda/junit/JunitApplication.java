package com.nanxinda.junit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JunitApplication {

    public static void main(String[] args) {
        /// 真正启动容器
        SpringApplication.run(JunitApplication.class, args);
    }

}
/// @SpringBootApplication间接包含@ComponentScan，默认会扫描该类所在的包及其所子包
/// @EnableAutoConfiguration会开启 Spring Boot 自动配置（比如Tomcat、web相关的bean等等）
//@Target({ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan(
//    excludeFilters = {@Filter(
//    type = FilterType.CUSTOM,
//    classes = {TypeExcludeFilter.class}
//), @Filter(
//    type = FilterType.CUSTOM,
//    classes = {AutoConfigurationExcludeFilter.class}
//)}
//)
//public @interface SpringBootApplication {}

/// 这个类同时也是会被识别为配置类
//@Target({ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Configuration
//@Indexed
//public @interface SpringBootConfiguration {
//}
