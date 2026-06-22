package com.nanxinda.login.strategy;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nanxinda.login.dto.Result;
import com.nanxinda.login.entity.User;
import com.nanxinda.login.strategy.abstractStrategy.LoginStrategy;
import com.nanxinda.login.utils.RegexUtils;
import org.springframework.stereotype.Component;

import static com.nanxinda.system.utils.SystemConstants.USER_NICK_NAME_PREFIX;

@Component("emailLogin")
public class EmailNumWay extends LoginStrategy {
    @Override
    protected boolean isInvalid(String sendNum) {
        return RegexUtils.isEmailInvalid(sendNum);
    }

    @Override
    protected Result getErrorMessage() {
        return Result.fail("邮箱号格式错误！");
    }

    @Override
    protected User getDataUser(String sendNum) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,sendNum);
        User user = userMapper.selectOne(lqw);
        if(user==null){
            User user1 = new User();
            user1.setIcon("");
            user1.setEmail(sendNum);
            user1.setNickName(USER_NICK_NAME_PREFIX+ RandomUtil.randomString(10));
            userMapper.insert(user1);
            return user1;
        }else {
            return user;
        }
    }
}
