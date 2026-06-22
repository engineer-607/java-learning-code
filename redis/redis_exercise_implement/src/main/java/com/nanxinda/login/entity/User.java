package com.nanxinda.login.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//NOTE set方法返回对象可以实现链式编程
@TableName("tb_user")
public class User {
    private final static Long serializedVersionID = 1L;
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    //手机号码
    private String phone;
    //邮箱号
    private String email;
    //密码
    private String password;
    //昵称
    private String nickName;
    //用户头像
    private String icon;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
}
