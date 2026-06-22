package com.nanxinda.login.dto;

import lombok.Data;
//NOTE 需要创建一个UserDTO只记录前端或者登录状态所需要的信息
// 并去除User中的隐私、敏感字段
@Data
public class UserDTO {
    private Long id;
    private String icon;
    private String nickName;
}
