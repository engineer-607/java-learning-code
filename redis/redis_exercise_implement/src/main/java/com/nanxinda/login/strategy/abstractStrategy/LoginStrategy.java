package com.nanxinda.login.strategy.abstractStrategy;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.nanxinda.login.dto.Result;
import com.nanxinda.login.dto.UserDTO;
import com.nanxinda.login.entity.User;
import com.nanxinda.login.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.nanxinda.login.utils.RedisConstants.*;

//流程
//用户输入手机号和验证码
//        ↓
//后端从 Redis 查验证码
//        ↓
//验证码正确
//        ↓
//查数据库，看用户是否存在
//        ↓
//不存在就创建用户
//        ↓
//登录成功
//        ↓
//生成 token
//        ↓
//把 token 和用户信息存 Redis
//        ↓
//把 token 返回给前端
@Slf4j
public abstract class LoginStrategy {
    @Autowired
    protected StringRedisTemplate redisTemplate;
    @Autowired
    protected UserMapper userMapper;
    //登录过程：
    public Result loginWay(String sendNum, String code){
        //1.校验格式是否正确
        if(isInvalid(sendNum)){
            //2.如果不符合，返回错误信息
            return getErrorMessage();
        }
        log.debug("号码：{},验证码:{}",sendNum,code);
        //3.从redis获取验证码并进行校验
        String cacheCode = redisTemplate.opsForValue().get(LOGIN_CODE_KEY +sendNum);
        if(code==null||!code.equals(cacheCode)){
            return Result.fail("验证码错误");
        }
        //4.如果一致则根据手机号码/邮箱号查询用户（没有就创建）
        User user = getDataUser(sendNum);
        log.debug("user:{}",user);
        //5.保存用户信息到redis中
        // 5.1 随机生成token，作为登录令牌
        String token = UUID.randomUUID().toString();
        // 5.2 将user转化为userDTO
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        log.debug("userDTO:{}",userDTO);
        //5.3 将userDTO中的属性转为HashMap进行存储
        //public class StringRedisTemplate extends RedisTemplate<String, String>
        // 由于StringRedisTemplate存储的field和value都是String类型所以需要将userMap
        // 的value转化为String类型避免类型异常
        Map<String,Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName,fieldValue)->fieldValue.toString())
        );
        //5.4 存储token
        String tokenKey = LOGIN_USER_KEY + token;
        redisTemplate.opsForHash().putAll(tokenKey,userMap);
        //设置tokenKey键在redis中存储的时间周期
        redisTemplate.expire(tokenKey,LOGIN_USER_TTL, TimeUnit.MINUTES);
        return Result.ok(token);

    }

    protected abstract boolean isInvalid(String sendNum);

    protected abstract Result getErrorMessage();

    protected abstract User getDataUser(String sendNum);


}
