package com.nanxinda.junit;

import com.nanxinda.junit.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JunitApplication.class)
/// 类型；测试类注解
/// 位置：测试类定义上方
/// 作用；设置JUnit加载的SpringBoot启动类
/// 相关属性：classes：设置SpringBoot启动类
class JunitApplicationTests {
    @Autowired
    private BookService bookService;

    @Test
    void saveTest() {
        bookService.save();
    }

}
