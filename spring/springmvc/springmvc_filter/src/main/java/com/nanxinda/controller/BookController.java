package com.nanxinda.controller;

import com.nanxinda.domain.Book;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> bookList = initBooks();

    @PostMapping
    public String save(@RequestBody Book book) {
        bookList.add(book);
        System.out.println("book save..." + book);
        return "{'module':'book save success'}";
    }

    @PutMapping
    public String update(@RequestBody Book book) {
        System.out.println("book update..." + book);
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId().equals(book.getId())) {
                bookList.set(i, book);
                return "{'module':'book update success'}";
            }
        }
        return "{'module':'book update fail'}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println("book delete..." + id);
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId().equals(id)) {
                bookList.remove(i);
                return "{'module':'book delete success'}";
            }
        }
        return "{'module':'book delete fail'}";
    }

    @GetMapping
    public List<Book> getAll() {
        System.out.println("book getAll...");
        return bookList;
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id) {
        System.out.println("book getById..." + id);
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @GetMapping("/search")
    public List<Book> getByCondition(@RequestParam(required = false) String type,
                                     @RequestParam(required = false) String name) {
        System.out.println("book getByCondition...type=" + type + ", name=" + name);
        List<Book> result = new ArrayList<Book>();
        for (Book book : bookList) {
            boolean typeMatch = type == null || "".equals(type) || book.getType().contains(type);
            boolean nameMatch = name == null || "".equals(name) || book.getName().contains(name);
            if (typeMatch && nameMatch) {
                result.add(book);
            }
        }
        return result;
    }

    private List<Book> initBooks() {
        List<Book> bookList = new ArrayList<Book>();

        Book book1 = new Book();
        book1.setId(1);
        book1.setType("computer");
        book1.setName("SpringMVC quickstart");
        book1.setDescription("base query demo");
        bookList.add(book1);

        Book book2 = new Book();
        book2.setId(2);
        book2.setType("computer");
        book2.setName("SpringMVC practice");
        book2.setDescription("case query demo");
        bookList.add(book2);

        Book book3 = new Book();
        book3.setId(3);
        book3.setType("program");
        book3.setName("Thinking in Java");
        book3.setDescription("condition query demo");
        bookList.add(book3);

        return bookList;
    }
}
