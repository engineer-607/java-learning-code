package com.nanxinda;

import com.nanxinda.dao.BookDao;
import com.nanxinda.dao.OrderDao;
import com.nanxinda.dao.UserDao;
import com.nanxinda.service.BookService;
import com.nanxinda.service.impl.BookServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    ///IoC（控制权反转）
    //main函数将对对象的创建的控制权移交给applicationContext.xml这个配置文件，但是使用权还在main函数这里
    //如果以后对BookDao这个接口的实现类发生变化，只需要在配置文件中修改就行，不需要再main函数中一个个修改
    public static void main( String[] args ) {
        //3.获取IoC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //4.获取bean
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        //getBean这个方法是ApplicationContext这个接口继承ListableBeanFactory这个父接口得到的方法
        bookDao.save();
        //获取bean
        BookService bookService = (BookService) ctx.getBean("bookService");
        bookService.save();
        //获取bean无论是通过id还是name获取，如果无法获取到，
        //将抛出异常NoSuchBeanDefinitionException:No bean named 'bookServiceImpl' available
        BookService bookService1 = (BookService) ctx.getBean("bookService");
        //地址相同，IoC默认创建的bean是单例
        System.out.println(bookService1==bookService);
        //实例创建（静态工厂）
        OrderDao orderDao = (OrderDao) ctx.getBean("orderDao");
        orderDao.save();
        //实例工厂创建对象
        UserDao userDao = (UserDao) ctx.getBean("userDao");
        userDao.save();
        //容器关闭前才会触发bean的销毁
        //ApplicationContext子接口为ConfigurableApplicationContext
        //这个接口中定义了close这个方法，而ClassPathXmlApplicationContext
        //这个实现类最终实现了close方法，所以需要向下转型改变编译类型才能使用close这个方法
        //手动关闭容器
        ((AbstractApplicationContext)ctx).close();//比较暴力
        //注册关闭钩子，在虚拟机退出前先关闭容器再退出虚拟机
//        ((AbstractApplicationContext) ctx).registerShutdownHook();
    }
}
