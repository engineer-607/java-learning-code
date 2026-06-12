package com.nanxinda.dao.impl;

import com.nanxinda.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void update() {
        System.out.println("book dao update...");
    }

    @Override
    public void delete() {
        System.out.println("book dao delete...");

    }

    @Override
    public int select() {
        System.out.println("select is running");
        return 100;
    }

    @Override
    public void save() {
//        //记录程序开始执行的时间
//        Long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            System.out.println("book dao save...");
//        }
//        //记录程序结束执行的时间
//        Long ednTime = System.currentTimeMillis();
//        Long totalTime = ednTime - startTime;
//        System.out.println("执行消耗的时间"+totalTime+"s");
        System.out.println("book dao save...");

    }
}
