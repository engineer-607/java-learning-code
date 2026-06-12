package com.nanxinda;

import com.nanxinda.config.SpringConfig;
import com.nanxinda.dao.BookDao;
import com.nanxinda.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class App 
{
    public static void main( String[] args )
    {
        //这个是在配置文件下的创建容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) context.getBean("bookDao");
        System.out.println(bookDao);
        BookService bookService = (BookService) context.getBean(BookService.class);
        System.out.println(bookService);
        ((ConfigurableApplicationContext)context).close();

        //在配置类下的创建容器
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookService bookService1 = ctx.getBean(BookService.class);
        bookService1.save();

        //第三方bean的管理
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource);


    }
}
