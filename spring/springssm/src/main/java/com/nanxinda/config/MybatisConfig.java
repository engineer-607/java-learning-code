package com.nanxinda.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
/// 持久化层引入的第三方bean，引入SqlSessionFactoryBean这个第三方bean，这是为了获取和归还
/// 连接对象；此外，还引入Mapper扫描配置，通过代理模式创建实现dao中的接口的对象
/// 容器获取该对象的管理权
public class MybatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.nanxinda.domain");
        return sqlSessionFactoryBean;
    }
    @Bean
    public MapperScannerConfigurer scannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.nanxinda.dao");
        return mapperScannerConfigurer;
    }
}
