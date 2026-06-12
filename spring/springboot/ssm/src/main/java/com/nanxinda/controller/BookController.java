package com.nanxinda.controller;

import com.nanxinda.domain.Book;
import com.nanxinda.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping
    public Result save(@RequestBody Book book){
        Boolean flag = bookService.save(book);
        return new Result(flag?Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Boolean flag = bookService.delete(id);
        return new Result(flag?Code.DELETE_OK:Code.DELETE_ERR,flag);
    }
    @PutMapping
    public Result update(@RequestBody Book book){
        Boolean flag = bookService.update(book);
        return new Result(flag?Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Book book = bookService.getById(id);
//        try {
//            int i = 1/0;
//        }catch (Exception e){
//            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR,"服务器超时，请重试！");
//        }
        String mgs = book!=null?"":"数据查询失败，请重试！";
        Integer code = book!=null?Code.GET_OK: Code.GET_ERR;
        return new Result(code,book,mgs);
    }

    @GetMapping
    public Result getAll(){
        List<Book> bookList = bookService.getAll();
        String mgs = bookList!=null?"":"数据查询失败，请重试！";
        Integer code = bookList!=null?Code.GET_OK: Code.GET_ERR;
        return new Result(code,bookList,mgs);
    }
}
