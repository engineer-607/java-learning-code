package com.nanxinda.login.strategy;

import com.nanxinda.login.strategy.abstractStrategy.SenderCodeStrategy;
import com.nanxinda.login.utils.RegexUtils;
import org.springframework.stereotype.Component;

@Component("email")
public class SendEmailNumWay extends SenderCodeStrategy {
    @Override
    protected String getErrorMessage() {
        return "邮箱号格式不正确";
    }

    @Override
    protected boolean isInvalid(String sender) {
        if(RegexUtils.isEmailInvalid(sender)){
            return true;
        }
        return false;
    }
}
