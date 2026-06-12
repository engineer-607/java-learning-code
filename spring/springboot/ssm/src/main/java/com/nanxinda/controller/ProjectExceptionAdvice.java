package com.nanxinda.controller;

import com.nanxinda.exception.BusinessException;
import com.nanxinda.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
/// 自动扫描容器中所有带 @RestController 或 @Controller 注解的类，并把它们天然地作为切入点
/// 底层其实就是用环绕通知加 try-catch
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    /// 类型：方法注解
    /// 位置：专用于异常处理的控制器方法上方
    /// 作用：设置指定异常的处理方案，功能等同于控制器方法，出现异常后种植原始控制器执行，并转入
    /// 当前方法执行
    public Result doException(Exception e){
        return new Result(Code.SYSTEM_UNKNOWN_ERR,null,"服务器繁忙，请稍后再试");
    }

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException exception){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员
        return new Result(exception.getCode(),null,exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException exception){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员
        return new Result(exception.getCode(),null,exception.getMessage());
    }


}
