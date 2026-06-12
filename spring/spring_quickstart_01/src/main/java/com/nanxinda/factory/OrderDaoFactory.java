package com.nanxinda.factory;

import com.nanxinda.dao.OrderDao;
import com.nanxinda.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {
    public static OrderDao getOrderDao(){
        return new OrderDaoImpl();
    }
}
