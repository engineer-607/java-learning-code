package com.nanxinda.login.utils;
//NOTE 工具常量类：1.不被继承2.不可创建对象3.直接通过类名访问变量
public final class RegexPatterns {
    private RegexPatterns() {
    }
    // NOTE 正则符号
    //  ^        开头
    //  $        结尾
    //  \d       数字
    //  \w       字母、数字、下划线
    //  .        任意一个字符
    //  *        出现 0 次或多次
    //  +        出现 1 次或多次
    //  ?        出现 0 次或 1 次
    //  {6}      刚好出现 6 次
    //  {4,32}   出现 4 到 32 次
    //  []       里面任选一个字符
    //  |        或
    //  ()       分组
    /**
     * 手机号正则
     */
    public static final String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-35-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    /**
     *邮箱正则
     */
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 密码正则。4~32位的字母、数字、下划线
     */
    public static final String PASSWORD_REGEX = "^\\w{4,32}$";
    /**
     * 验证码正则, 6位数字或字母
     */
    public static final String VERIFY_CODE_REGEX = "^[a-zA-Z\\d]{6}$";

}

