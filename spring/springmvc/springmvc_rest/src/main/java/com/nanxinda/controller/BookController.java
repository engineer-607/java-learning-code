package com.nanxinda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/// 争对UserController的简化案例
//@Controller
//1.将重复重现的网址写在类上
@RequestMapping("books")
//2.将重复出现的ResponseBody写在类上
//@ResponseBody
//3.Controller和ResponseBody这两个注解由于经常出现可以改为RestController
@RestController
public class BookController {
//    @RequestMapping(value = "/books",method = RequestMethod.POST)
    @PostMapping
    //可以用PostMapping来代替@RequestMapping中对http请求方法的定义
    public String save(){
        System.out.println("book save...");
        return "{'module':'book save}";
    }
//    @RequestMapping(value = "/books/{id}",method = RequestMethod.GET)

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id){
        System.out.println("book getById..."+id);
        return "{'module':'book getById}";
    }
//    @RequestMapping(value = "/books",method = RequestMethod.GET)

    @GetMapping
    public String getAll(){
        System.out.println("book getAll...");
        return "{'module':'book getAll}";
    }
//    @RequestMapping(value = "/books/{id}",method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        System.out.println("book delete"+id);
        return "{'module':'book delete}";
    }
//    @RequestMapping(value = "/books",method = RequestMethod.PUT)
    @PutMapping
    public String update(){
        System.out.println("book update");
        return "{'module':'book update}";
    }
}
