package com.nanxinda.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 庞绍祥
 * @since 2026-06-10
 */
@Getter
@Setter
@ToString
@TableName("tb_stu_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String name;

    private Integer age;

    private String gender;

    private String phone;

    private String email;

    private LocalDateTime createTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;
}
