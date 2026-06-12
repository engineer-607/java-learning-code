package com.nanxinda.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataAdvice {
    @Pointcut("execution(* com.nanxinda..read*(..))")
    public void pt(){

    }
    @Around("pt()")
    public Object trim(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof String){
                args[i]=((String)args[i]).trim();
            }
        }
        return pjp.proceed(args);
    }
}
