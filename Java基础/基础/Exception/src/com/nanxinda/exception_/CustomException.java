package com.nanxinda.exception_;
/*
# 一览表

| 关键字  |  意义 | 位置  |  后面跟的东西  |
|--------|------|------|--------------|
| throws | 异常处理的一种方式 | 方法声明处 | 异常类型 |
| throw  | 手动生成异常对象的关键字 | 方法体中 | 异常对象 |
 */
public class CustomException {
    public static void main(String[] args) {
        int age =80;
        if(!(age>=18&&age<=120)){
            throw new AgeException("年龄异常");
        }
        System.out.println("您的年龄正确");
    }
}
/*
自定义异常：
1）如果继承RuntimeException，则为运行异常
2）如果继承Exception，则为编译异常
 */
class AgeException extends RuntimeException{
    public AgeException(String message) {
        super(message);
    }
}