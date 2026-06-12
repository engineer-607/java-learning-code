package com.nanxinda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.nanxinda")
//开启Spring对AOP注解驱动支持
@EnableAspectJAutoProxy
public class SpringConfig {

}
