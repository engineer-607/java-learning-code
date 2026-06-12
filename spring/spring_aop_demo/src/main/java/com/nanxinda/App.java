package com.nanxinda;

import com.nanxinda.config.SpringConfig;
import com.nanxinda.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/// AOP面向切面编程，一种编程范式，指导开发者如何组织程序结构；作用：在不惊动原始设计的基础上为其进行功能
/// 增强（Spring理念：无入侵式编程）
/// 具体概念：
/// 1.连接点(JoinPoint)：程序执行过程中的任意位置，粒度为执行方法、抛出异常、设置变量等
/// 在SpringAOP中，理解为方法的执行
/// 2.切入点(PointCut)：匹配连接点的式子
/// 在SpringAOP中，一个切入点可以只描述一个具体方法，也可以匹配多个方法
/// 一个具体方法：com.nanxinda.dao包下的BookDao接口中的无形参无返回值的save方法
/// 匹配多个方法：所有的save方法，所有get开头的方法，所有以Dao结尾的接口中的任意方法...
/// 3.通知：在切入点处执行的操作，也就是共性功能
/// 在SpringAOP中，功能最终以方法的形式呈现
/// 通知类：定义通知的类
/// 4.切面：描述通知与切入点的对应关系
public class App
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bean = ctx.getBean(BookDao.class);
        System.out.println("===before/afterReturning===");
        bean.update();
        System.out.println("===after===");
        bean.save();
        System.out.println("===around===");
        bean.delete();
        System.out.println("===aroundReturn===");
        int num = bean.select();
        System.out.println(num);

    }
}
