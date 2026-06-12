package com.nanxinda;

import com.nanxinda.dao.BookDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
/**
 * @Author:CVengineer
 */
public class AppForBeanFactory {
    public static void main(String[] args) {
        //BeanFactory是延迟加载bean，而ApplicationContext是立即加载bean（即在启动容器时便加载好bean）
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        BookDao bookDao = factory.getBean(BookDao.class);
        bookDao.save();

    }
}
