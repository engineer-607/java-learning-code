package com.nanxinda.login.strategy.abstractStrategy;

import cn.hutool.core.util.RandomUtil;
import com.nanxinda.login.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

import static com.nanxinda.login.utils.RedisConstants.LOGIN_CODE_KEY;
import static com.nanxinda.login.utils.RedisConstants.LOGIN_CODE_TTL;

//NOTE 策略模式分类方式应该根据是否有相同的输入、相同的动作、相同的返回结果、相同的业务阶段
// 来划分，而不是盲目地将同一业务阶段的不同策略全都塞到同一策略里
@Slf4j
public abstract class SenderCodeStrategy {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public Result sendCode(String sender){
        //校验手机号/邮箱号是否正确
        if(isInvalid(sender)){
            return Result.fail(getErrorMessage());
        }
        //生成随机验证码
        String code = RandomUtil.randomNumbers(6);
        System.out.println(code);
        //redis保存该验证码(需要设置验证码有效期)
        redisTemplate.opsForValue().set(LOGIN_CODE_KEY + sender,code,LOGIN_CODE_TTL, TimeUnit.MINUTES);
        //模拟发送验证码
        log.debug("验证码发送成功，发送人{},验证码{}",sender,code);
        //返回成功发送的信息
        return Result.ok();
    }
    protected abstract String getErrorMessage();
    protected abstract boolean isInvalid(String sender);
}
