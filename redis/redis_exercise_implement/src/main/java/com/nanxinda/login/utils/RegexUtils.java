package com.nanxinda.login.utils;

import cn.hutool.core.util.StrUtil;

public class RegexUtils {

    public static boolean isPhoneInvalid(String phoneNum){
        return mismatch(phoneNum,RegexPatterns.PHONE_REGEX);
    }
    public static boolean isEmailInvalid(String emailNum){
        return mismatch(emailNum,RegexPatterns.EMAIL_REGEX);
    }
    //NOTE 检验是否不符合正则表达式
    // true：不符合；false：符合
    private static boolean mismatch(String string,String regex){
        //判断字符串是否 null、空字符串、或者全是空白字符
        //避免空指针
        if(StrUtil.isBlank(string)){
            return true;
        }
        //将字符串与正则规则进行匹配
        return !string.matches(regex);
    }

}
