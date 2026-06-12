package com.nanxinda.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.nanxinda")
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybatisConfig.class})
/// Spring事务作用：在数据层或者业务层保障一系列的数据库操作同成功同失败
/// 3.开启注解式事务驱动
@EnableTransactionManagement
public class SpringConfig {

}
