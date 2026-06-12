package com.nanxinda.factory;

import com.nanxinda.dao.UserDao;
import com.nanxinda.dao.impl.UserDaoImpl;

public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();

    }
}
