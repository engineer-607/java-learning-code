package com.nanxinda.service.impl;

import com.nanxinda.controller.Code;
import com.nanxinda.dao.BookDao;
import com.nanxinda.domain.Book;
import com.nanxinda.exception.BusinessException;
import com.nanxinda.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;

    @Override
    public boolean save(Book book) {
        bookDao.save(book);
        return true;
    }

    @Override
    public boolean update(Book book) {
        bookDao.update(book);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        bookDao.delete(id);
        return true;
    }

    @Override
    public Book getById(Integer id) {
        if(id<0){
           throw new BusinessException(Code.BUSINESS_ERR,"请输入正确的id");
        }
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
