package com.nanxinda.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.nanxinda.service","com.nanxinda.dao"})
public class SpringConfig {
}
