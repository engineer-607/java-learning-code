package com.nanxinda.service;

import com.nanxinda.domain.Book;

import java.util.List;

public interface BookService {
    public boolean save(Book book);
    public boolean update(Book book);
    public boolean delete(Integer id);
    public Book getById(Integer id);
    public List<Book> getAll();
}
