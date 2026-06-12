package com.nanxinda.dao.impl;

import com.nanxinda.dao.BookDao;

public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao service");
    }
    //bean生命周期
    //生命周期：从创建到消亡的完整过程
    //bean生命周期：bean从创建到销毁的整体过程
    //bean生命周期控制：在bean创建后到销毁前做一些事情
    //表示bean初始化对应的操作
    public void init(){
        System.out.println("init...");
    }
    //表示bean销毁对应的操作
    public void destroy(){
        System.out.println("destroy...");
    }
    /// bean生命周期
    /// *初始化容器
    /// 1.创建容器（内存分配）
    /// 2.执行构造方法
    /// 3.执行属性注入（set操作）
    /// 4.执行bean初始化方法
    /// *使用bean
    /// 1.执行业务操作
    /// *关闭/销毁容器
    /// 1.执行bean销毁方法
}
