package com.nanxinda.junit.service.impl;

import com.nanxinda.junit.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl  implements BookService {
    @Override
    public void save() {
        System.out.println("book service is running...");
    }
}
