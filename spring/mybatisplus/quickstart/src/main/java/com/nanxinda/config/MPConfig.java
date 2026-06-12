package com.nanxinda.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MPConfig {
    //NOTE 设置分页拦截器作为Spring管理的bean
    @Bean
    public MybatisPlusInterceptor plusInterceptor(){
        //1.定义Mp拦截器（所有 Mapper 执行 SQL 时，都会经过这个拦截器链）
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //2.添加具体拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //NOTE 添加乐观锁拦截器
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
