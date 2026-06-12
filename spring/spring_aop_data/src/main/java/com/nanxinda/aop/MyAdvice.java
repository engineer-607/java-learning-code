package com.nanxinda.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
/// AOP通知获取数据
/// 1.获取切入点方法的参数
/// JoinPoint：适用于前置、后置、返回后、抛出异常后通知
/// ProceedJointPoint：适用于环绕通知
/// 2.获取切入点方法返回值
/// 返回后通知
/// 环绕通知
/// 3.获取切入点方法运行异常信息
/// 抛出异常后通知
/// 环绕通知
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(* com.nanxinda..find*(..))")
    public void pt(){

    }
//    @Before("pt()")
    public void before(JoinPoint joinPoint){
        ///获取切入点方法参数（注意：如果通知方法有JoinPoint类型的参数，必须放在第一个）
        Object[] objects = joinPoint.getArgs();
        System.out.println(Arrays.toString(objects));
        System.out.println("before advice..");
    }
//    @After("pt()")
    public void after(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        System.out.println(Arrays.toString(objects));
        System.out.println("after advice...");
    }
//    @Around("pt()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //ProceedingJoinPoint是JoinPoint的子类
        Object[] objects = proceedingJoinPoint.getArgs();
        objects[0] = 666;
        /// 可以校捡并修改参数
        return (String) proceedingJoinPoint.proceed(objects);
    }
    @AfterReturning(value = "pt()",returning = "result")
    public void afterReturning(Object result){
        /// result为目标方法的返回值
        System.out.println("afterReturning..."+result);
    }
    @AfterThrowing(value = "pt()",throwing = "throwable")
    public void afterThrowing(Throwable throwable){
        System.out.println("afterThrowing..."+throwable);
    }


}
