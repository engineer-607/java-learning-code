package com.nanxinda.login.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.nanxinda.login.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nanxinda.login.utils.RedisConstants.LOGIN_USER_KEY;

//NOTE 登录拦截器，访问一些接口时需要先检验登录状态
public class LoginInterceptor implements HandlerInterceptor {
    //NOTE 无法依赖注入，因为LoginInterceptor是我们在配置中自己创建的，不过依赖可以
    // 加在配置类中，通过构造参数穿进来
    private StringRedisTemplate redisTemplate;

    public LoginInterceptor(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.从前端请求中获取token
        String token = request.getHeader("authorization");
        //2.现对token进行检验
        if(StrUtil.isBlank(token)){
            response.setStatus(401);
            return false;
        }
        //3.查询用户是否存在
        String tokenKey = LOGIN_USER_KEY + token;
        //entries方法可以拿到键对应的整个对象map
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(tokenKey);
        if(entries==null){
            // 如果tokenKey没有对应的值说明登录过期，返回401状态
            response.setStatus(401);
            return false;
        }
        UserDTO userDTO = BeanUtil.fillBeanWithMap(entries, new UserDTO(), false);
        //存储UserDTO到线程属性当中，便于后续使用
        UserHolder.saveUser(userDTO);

        //NOTE 在登录验证中已经有过对tokenKey的时间配置，但是这个时间配置并不随访问而刷新
        // 即时间从创建那一刻开始算起，但是这不利于用户的使用，所以在检验登录状态时需要手动
        // 对token时间配置进行刷新
        redisTemplate.expire(tokenKey,RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserHolder.remove();
    }
}
