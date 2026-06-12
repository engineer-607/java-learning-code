package com.nanxinda.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
//@Component表示该通知类由spring来接管
@Component
//@Aspect表示该类为切面类（增强逻辑的集合）
@Aspect
//2.定义通知类
public class MyAdvice {
    //定义切入点
    @Pointcut("execution(void com.nanxinda.dao.BookDao.update())")
    //切入式表达式标准格式：动作关键词(访问修饰符 返回值 包名.类/接口名.方法名(参数)异常名)
    //动作关键词：描述切入点的行为动作，例如execution表示执行到指定切入点
    //访问修饰符：public,private等，可以省略
    //异常名：方法定义中抛出指定异常，可以省略
    //可以通过通配符描述任意符号，可以独立出现，也可以作为前缀或者后缀的匹配符出现
    ///*:单个独立的任意符号，可以独立出现，也可以作为前缀或者后缀的匹配符出现
    //execution( public * com.nanxinda.*.UserService.find* (*) )
    //匹配com.nanxinda包下的任意包中的UserService类或者接口中所有find开头的带有一个参数的方法
    ///..:多个连续的任意符号，可以独立出现，常用于简化包名与参数的书写
    //execution(public User com.UserService.findById(..))
    //匹配com包下的任意包中的UserService类或者接口中所有名称为findById的方法
    /// +:专用于匹配子类类型
    //execution(* *..*Service+.*(..))
    //书写技巧
    //所有代码按照标准规范开发，否则以下技巧全部失效
    //1.描述切入点通常描述接口，而不描述实现类
    //2.访问控制修饰符针对接口开发均采用public描述（可省略访问控制修饰符描述）
    //3.返回值类型对于增删改类使用精准类型加速匹配，对于查询类使用*通配快速
    //4.描述包名书写尽量不使用..匹配，效率过低，常用*做单个包描述匹配，或精准匹配
    //5.接口名/类名书写名称与模块相关的采用*匹配，例如UserService书写成*Service，绑定业务层接口名
    //6.方法名书写以动词进行精准匹配，名词采用*匹配，例如getById书写成getBy*,selectAll书写成select*
    //7.参数规则较为复杂，根据业务方法灵活调整
    //8.通常不使用异常作为匹配规则
    //3.切入点定义依托一个不具有实际意义的方法进行，即无参数，无返回值，方法体无实际逻辑
    private void pt(){

    }
    @Pointcut("execution(* com.nanxinda..save())")
    public void pt1(){

    }
    @Pointcut("execution(* com.nanxinda..delete())")
    public void pt2(){

    }
    @Pointcut("execution(int com.nanxinda..select())")
    public void pt3(){

    }
    //绑定切入点和通知，并指定通知添加到原始连接点的具体执行位置，形成切面
    @Before("pt()")
    //AOP通知共分为5种：
    //1.前置通知
    //2.后置通知
    //3.环绕通知（重点）
    //4.返回后通知（了解）
    //5.抛出异常后通知（了解）

    //2.定义通知（增强逻辑）
    public void before(){
        Long startTime = System.currentTimeMillis();
        System.out.println(startTime);
    }
    @After("pt1()")
    public void after() {
        System.out.println("after advice ...");
    }
    @Around("pt2()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        //如果目标方法成功被AOP拦截，那么joinPoint这个对象中就包含当前类当前方法、参数、返回值、代理对象等等信息。
        //signature是方法签名，能够获得目标方法所在对象的类以及方法名本身
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        System.out.println("around before advice ...");
        /// 表示对原始操作的调用
        joinPoint.proceed();
        System.out.println("around after advice ...");
    }
    //1.环绕通知必须依赖形参ProceedingJoinPoint才能实现对原始方法的调用，进而实现原始方法调用前后同时添加通知
    //2.通知中如果未使用ProceedingJoinPoint对原始方法进行调用将跳过原始方法的执行
    //3.对原始方法的调用可以不接收返回值，通知方法设置成void即可，如果接收返回值，必须设定为Object类型
    //4.原始方法的返回值如果是void类型，通知方法的返回值类型可以设置成void，也可以设置成Object
    //5.由于无法预知原始方法运行后是否会抛出异常，因此环绕通知方法必须抛出Throwable对象

    /// 环绕方法包住目标方法，可以决定返回什么，甚至可以决定目标是否执行（和最终的代理方法最贴近）
    /// around方法更加贴合我一开始对AOP的认知：将散落在各个角落的公共逻辑抽取出来
    /// 但事实上AOP更应该理解为一种在不更改原有业务代码的情况下在执行流程过程中的
    /// 某些地方增强功能（即代理模式）
    @Around("pt3()")
    public Object aroundSelect(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before advice ...");
        /// 表示对原始操作的调用
        Integer res = (Integer) joinPoint.proceed();
        System.out.println("around after advice ...");
//        return 200;
        return res;
    }

    @AfterReturning("pt()")//与After的区别是只有目标方法没有抛异常的时候才会运行
    public void afterReturning() {
        System.out.println("afterReturning advice ...");
    }
    @AfterThrowing("pt3()")//只有抛出异常之后才运行
    public void afterThrowing() {
        System.out.println("afterThrowing advice ...");
    }
}
/// AOP本质使用的是代理模式
///AOP工作流程
///1.Spring容器启动
///2.读取所有切面配置中的切入点
///3.初始化bean，判定bean对应类中的方法是否匹配到任意切入点
/// *匹配失败，创建对象
/// *匹配成功，创建原始对象（目标对象）的代理对象
///4.获取bean执行方法
/// *获取bean，调用方法并执行，完成操作
/// *获取bean是代理对象时，根据代理对象的运行模式运行原始方法与增强模式，完成操作

/// 目标对象（Target）：原始功能去掉共性功能对应的类产生的对象，这种对象是无法直接完成最终工作的
///代理（Proxy）：目标对象无法直接完成工作，需要对其进行功能回填，通过原始对象的代理对象实现