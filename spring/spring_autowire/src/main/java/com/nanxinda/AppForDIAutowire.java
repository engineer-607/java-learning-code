package com.nanxinda;

import com.nanxinda.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @Author:CVengineer
 */
public class AppForDIAutowire {
    public static void main(String[] args) throws SQLException {
        //配置文件的两种方式
        //1.加载类路径下的配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从文件系统下加载配置文件
        ApplicationContext ctx1 = new FileSystemXmlApplicationContext("D:\\spring\\spring_autowire\\src\\main\\resources\\applicationContext.xml");
        //获取bean的三种方式：
        //1.直接获取，不过要强转
        BookService bookService = (BookService) ctx.getBean("bookService");
        //2.规定返回的类型(实际上底层使用泛型，可以确保类型安全)
        BookService bookService1 = ctx.getBean("bookService",BookService.class);
        //3.通过类型获取(需要确保容器中的bean只有一个)
        BookService bookService2 = ctx.getBean(BookService.class);
        bookService.save();
        //对数据源对象进行管理
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        Connection conn = dataSource.getConnection();
        System.out.println(conn);


    }
}
