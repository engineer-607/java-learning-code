package com.nanxinda.controller;

import com.nanxinda.domain.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    List<Book> bookList = new ArrayList<Book>();
    @PostMapping
    public String save(@RequestBody com.nanxinda.domain.Book book){
        bookList.add(book);
        System.out.println("book save ==>"+book);
        return "{'module':'book save success'}";
    }
    @GetMapping
    public List<Book> getAll() {
        Book book1 = new Book();
        book1.setType("计算机");
        book1.setName("SpringMVc入门教程");
        book1.setDescription("小试牛刀");
        bookList.add(book1);
        Book book2 = new Book();
        book2.setType("计算机");
        book2.setName("SpringMVc实战教程");
        book2.setDescription("代宗师");
        bookList.add(book2);
        return bookList;
    }

}
