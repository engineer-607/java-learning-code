package com.nanxinda.service;

import com.nanxinda.config.SpringConfig;
import com.nanxinda.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ServiceTest {
    @Autowired
    private BookService bookService;
    @Test
    public void testGetById(){
        System.out.println(bookService.getById(16));
    }
    @Test
    public void testGetAll(){
        List<Book> list = bookService.getAll();
        for (Book book : list) {
            System.out.println(book);
        }

    }
}
