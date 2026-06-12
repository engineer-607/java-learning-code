package com.nanxinda;

import com.nanxinda.config.SpringConfig;
import com.nanxinda.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController bean = applicationContext.getBean(UserController.class);
        System.out.println(bean);
    }
}
