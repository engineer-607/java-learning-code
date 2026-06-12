package com.nanxinda.service.impl;

import com.nanxinda.dao.BookDao;
import com.nanxinda.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@SuppressWarnings({"all"})
//这种只能通过类型获得bean
//@Component
//注意：自动装配基于反射设计创建对象并暴力反射对应属性为私有属性初始化数据，因此无需提供setter方法
//注意：自动装配建议使用无参构造创建对象（默认），如果不提供对应构造方法，请提供唯一的构造方法
@Service
public class BookServiceImpl implements BookService {
    /// @Autowired实现原来标签中的依赖注入（DI）
    @Autowired
    ///如果出现BookDao多个实现类,使用注解@Qualifier，指定需要用到的实现类
    /// @Qualifier注解无法单独使用，必须配合@Autowired注解使用
    @Qualifier("bookDao")
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void save(){
        bookDao.save();
        System.out.println("book service save...");
    }
}
