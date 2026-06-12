package com.nanxinda.service.impl;

import com.nanxinda.dao.BookDao;
import com.nanxinda.service.BookService;

public class BookServiceImpl implements BookService {
    //bean本质就是对象，创建bean使用构造方法完成
    //私有同样能调用，说明IoC在创建对象的时候使用的反射原理
    //调用的构造方法是无参的构造器
//    private BookServiceImpl() {
//        System.out.println("bookService is running...");
//    }

    /// 依赖注入方式选择
    /// 1.强制依赖使用构造器进行，使用setter注入有概率不进行注入导致null对象出现
    /// 2.可选依赖使用setter注入进行，灵活性强
    /// 3.Spring框架倡导使用构造器，第三方框架内部大多数采用构造器注入的形式进行数据初始化，相对严谨
    /// 4.如果有必要可以两者同时使用，使用构造器注入完成强制依赖注入，使用setter注入完成可选依赖的注入
    /// 5.实际开发过程中还要根据实际情况分析，如果受控对象没有提供setter方法就必须使用构造器注入
    /// 6.自己开发的模块使用setter注入
    private BookDao bookDao;
    /// setter注入——基本数据类型
    private String dataBaseName;
    private Integer connectionNum;

    /// 构造器注入——引用数据类型，基本数据类型
    public BookServiceImpl(BookDao bookDao, String dataBaseName, Integer connectionNum) {
        this.bookDao = bookDao;
        this.dataBaseName = dataBaseName;
        this.connectionNum = connectionNum;
    }

    /// 构造器注入——引用数据类型


    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public void setConnectionNum(Integer connectionNum) {
        this.connectionNum = connectionNum;
    }

    @Override
    public void save() {
        System.out.println("book service save..."+dataBaseName+connectionNum);
        bookDao.save();
    }
    /// setter注入——引用类型
    //5.书写需要依赖的属性的set方法
    //这里必须写属性的set方法，配置文件applicationContext.xml才能识别注入
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
