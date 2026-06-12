package com.nanxinda.config;

import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
/// 负责初始化持久化容器和表现层容器（类似于持久化层的ApplicationContext，会创建IoC容器）
/// 无需加注解，Tomcat通过JavaSPI机制会硬编码地找到实现ServletContainerInitializer这个接口，找到
/// Spring独立类SpringServletContainerInitializer（Spring早已内置实现），而在这个类上
/// 有注解@HandlesTypes(WebApplicationInitializer.class)，Tomcat会接着寻找实现
/// WebApplicationInitializer.class这个接口的类，而AbstractAnnotationConfigDispatcherServletInitializer
/// 恰是这个接口的实现类，最后Tomcat便成功识别到这个初始化容器的类
public class ServletContainerInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    //中文乱码问题
    @Nullable
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}
