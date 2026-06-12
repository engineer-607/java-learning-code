package com.nanxinda.factory;

import com.nanxinda.dao.UserDao;
import com.nanxinda.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

public class UserDaoFactoryBean implements FactoryBean<UserDao>, DisposableBean {
    private UserDaoImpl userDao;
    ///spring对UserDaoImpl这个类的对象userDao并不是直接控制，而是间接控制（控制权偏移），App这个类中
    /// 的main函数将对创建UserDaoImpl这个类的控制权通过getBean("userDao")交给了IoC，
    /// 然后IoC在识别bean标签发现这个userDao所指向的类UserDaoFactoryBean，便在单例池中创建
    /// 相关的对象，而由于该类实现了FactoryBean，所以在调用getBean方法时会调用getObject这个方法
    /// 这个方法就会创建最终所需要的UserDaoImpl对象，但该对象并不是存储在单例池中，而是在FactoryBean专属缓存里
    /// 所以spring的IoC不会直接管控这个对象，也就不会掌握这个对象的初始化和销毁（这些是由工厂掌握，但也可以理解为
    /// IoC间接掌控）
    @Nullable
    @Override
    public UserDao getObject() throws Exception {
        userDao = new UserDaoImpl();
        userDao.afterPropertiesSet();
        return userDao;
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return false;//false表示不是单例，true表示是单例
    }

    @Override
    public void destroy() throws Exception {
        if(this.userDao!=null){
            this.userDao.destroy();
        }
    }
}
