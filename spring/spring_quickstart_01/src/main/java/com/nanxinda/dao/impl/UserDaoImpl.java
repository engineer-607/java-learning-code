package com.nanxinda.dao.impl;

import com.nanxinda.dao.UserDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class UserDaoImpl implements UserDao, InitializingBean, DisposableBean {

    @Override
    public void save() {
        System.out.println("user dao save...");
    }
    //该方法实在属性设置之后再执行的
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy...");
    }
}

