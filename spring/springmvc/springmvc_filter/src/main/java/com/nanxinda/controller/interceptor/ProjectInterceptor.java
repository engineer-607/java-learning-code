package com.nanxinda.controller.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ProjectInterceptor implements HandlerInterceptor {
    /// 拦截器
    /// 拦截器是一种动态拦截方法调用的机制，在SpringMVC中动态拦截控制器方法的执行
    /// 作用：1.在指定的方法调用前后执行先设定的代码
    ///      2.阻止原始代码的执行

    /// 拦截器与过滤器区别
    /// 归属不同：Filter属于Servlet技术，Interceptor属于SpringMVc技术
    /// 拦截内容不同：Filter对所有访问进行增强，Interceptor仅针对SpringMVc的访问进行增强

    //在拦截的原始代码之前运行
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        String header = request.getHeader("User-Agent");
//        System.out.println(header);
        //request：请求对象
        //response：响应对象
        //handler：被调用的处理器对象，本质是一个方法对象，对反射技术中的Method对象进行了再包装
//        Method method = ((HandlerMethod) handler).getMethod();
//        System.out.println(method.getName());
        System.out.println("preHandle...");
        return true;
        //如果返回false，那么切入点（原始代码不会执行）
    }

    //在拦截的原始代码之后运行
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        //modelAndView:如果处理执行完成具有返回结果，可以找到读取到对应数据与页面信息，并进行调整
        System.out.println("postHandle...");
    }

    //在postHandle方法之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
/*
 * 多拦截器执行顺序
 *
 * 配置顺序：
 *
 *        pre1              pre2              pre3            controller          post3          post2          post1
 *         |                 |                 |                  |                  |              |              |
 *         v                 v                 v                  v                  v              v              v
 * 情况一：全部 preHandle 返回 true
 *        pre1  --->        pre2  --->        pre3  --->      controller  --->     post3  --->     post2  --->     post1
 *          |                                                                                                      |
 *          |------------------------------------------------------------------------------------------------------|
 *                                                     after3  --->  after2  --->  after1
 * 情况二：pre3 返回 false
 *
 *        pre1  --->        pre2  --->        pre3(false)
 *                                                   |
 *                                                   v
 *                                      after2  ---> after1
 * 情况三：pre2 返回 false
 *
 *        pre1  --->        pre2(false)
 *                         |
 *                         v
 *                    after1
 * 情况四：pre1 返回 false
 *        pre1(false)
 * 规律：
 * 1. preHandle 按拦截器配置顺序执行：pre1 -> pre2 -> pre3
 * 2. 只有所有 preHandle 都返回 true，才会进入 controller
 * 3. postHandle 只有进入 controller 后才会执行，
 *    并且执行顺序与 preHandle 相反：post3 -> post2 -> post1
 * 4. afterCompletion 也按逆序执行：after3 -> after2 -> after1
 * 5. 如果某个 preHandle 返回 false：
 *    - 当前拦截器后面的 preHandle 不执行
 *    - controller 不执行
 *    - postHandle 不执行
 *    - 只执行前面已经成功放行过的拦截器的 afterCompletion
 */
