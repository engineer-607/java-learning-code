package com.nanxinda.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.nanxinda.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JdbcConfig {
    @Value("com.mysql.cj.jdbc.Driver")
    private String driver;
    @Value("jdbc:mysql://127.0.0.1:3306/itcast?useSSL=false&serverTimezone=Asia/Shanghai")
    private String url;
    @Value("ubuntu")
    private String userName;
    @Value("123456")
    private String password;
    //1.定义一个方法获得要管理的对象
    //2.添加@Bean，表示当前方法的返回值是一个bean
    @Bean
    public DataSource dataSource(BookDao bookDao){
        System.out.println(bookDao);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;


    }
}
