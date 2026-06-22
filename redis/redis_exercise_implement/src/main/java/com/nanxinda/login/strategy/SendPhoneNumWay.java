package com.nanxinda.login.strategy;

import com.nanxinda.login.strategy.abstractStrategy.SenderCodeStrategy;
import com.nanxinda.login.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("phone")
public class SendPhoneNumWay extends SenderCodeStrategy {

    @Override
    protected String getErrorMessage() {
        return "手机号格式不正确";
    }

    @Override
    protected boolean isInvalid(String sender) {
        if(RegexUtils.isPhoneInvalid(sender)){
            return true;
        }
        return false;
    }

}
