package com.nanxinda;

import com.nanxinda.config.SpringConfig;
import com.nanxinda.service.ResourceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        com.nanxinda.service.ResourceService resourceService = (ResourceService) ctx.getBean(ResourceService.class);
        boolean flag = resourceService.openURL("https://baidu.com","root ");
        System.out.println(flag);
    }
}
