package com.nanxinda.service.impl;

import com.nanxinda.dao.BookDao;
import com.nanxinda.service.BookService;

import java.util.stream.BaseStream;

/**
 * @Author:CVengineer
 */
public class BookServiceImpl implements BookService {
    /// 依赖自动装配特征
    /// 自动装配用于引用类型依赖注入，不能对简单类型进行操作
    /// 使用按类型装配时（byType）必须保障容器中具有指定名称的bean唯一，推荐使用
    /// 使用按名称装配时（byName）必须保障容器中具有指定名称的bean，因变量名与配置耦合，不推荐使用
    /// 自动装配优先级低于setter注入与构造器注入，同时出现时自动装配失效
    private BookDao bookDao;
    @Override
    public void save() {
        bookDao.save();
        System.out.println("book service save...");
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
