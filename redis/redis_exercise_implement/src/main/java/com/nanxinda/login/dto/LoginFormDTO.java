package com.nanxinda.login.dto;

import lombok.Data;

@Data
public class LoginFormDTO {
    //登录验证信息：验证类型，验证码，验证号码
    String type;
    String typeNum;
    String code;
}
