package com.nanxinda.login.strategy;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanxinda.login.dto.Result;
import com.nanxinda.login.entity.User;
import com.nanxinda.login.strategy.abstractStrategy.LoginStrategy;
import com.nanxinda.login.utils.RegexUtils;
import org.springframework.stereotype.Component;

import static com.nanxinda.system.utils.SystemConstants.USER_NICK_NAME_PREFIX;

@Component("phoneLogin")
public class PhoneNumWay extends LoginStrategy {
    @Override
    protected boolean isInvalid(String sendNum) {
        return RegexUtils.isPhoneInvalid(sendNum);
    }

    @Override
    protected Result getErrorMessage() {
        return Result.fail("手机号码格式错误！");
    }

    @Override
    protected User getDataUser(String sendNum) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getPhone,sendNum);
        User user = userMapper.selectOne(lqw);
        if(user==null){
            User user1 = new User();
            user1.setIcon("");
            user1.setPhone(sendNum);
            user1.setNickName(USER_NICK_NAME_PREFIX+ RandomUtil.randomString(10));
            userMapper.insert(user1);
            return user1;
        }else {
            return user;
        }
    }
}
