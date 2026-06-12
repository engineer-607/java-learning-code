package com.nanxinda.dao.impl;

import com.nanxinda.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//设置名称bookDao
@Component("bookDao")
@Scope("singleton")
//singleton表明单例，prototype表示非单例
public class BookDaoImpl implements BookDao {
    @Value("${name}")
    private String name;
    public void save(){
        System.out.println("book dao save..."+name);
    }
    //bean生命周期中的注解
    @PostConstruct
    //相当于标签中的init-method
    public void init(){
        System.out.println("init...");
    }
    @PreDestroy
    //相当于标签中的destroy-method
    public void destroy(){
        System.out.println("destroy...");
    }
}
