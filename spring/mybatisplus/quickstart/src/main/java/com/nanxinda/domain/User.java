package com.nanxinda.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;
//NOTE 如果表名不是user，需要在实体类上添加表名
///类型：类注解
///作用设置当前类与数据库表关系
@Data
//NOTE 该注解自动为当前实体类在编译期设置对应的get/set方法，toString方法，hashCode方法、equals方法
//NOTE 相当于包含下面注解
//@Setter
//@Getter
//@ToString
//@EqualsAndHashCode
@NoArgsConstructor
//NOTE 无参构造方法
@AllArgsConstructor
//NOTE 全参构造方法
public class User {
    @TableId(type = IdType.AUTO)
    //NOTE  AUTO(0):使用数据库id自增策略控制id生成
    //  NONE(1):不设置id生成策略（实体类字段不单独指定 id 生成方式，id一般来自全局配置、手动配置等）
    //  INPUT(2):用户手工输出id
    //  ASSIGN_ID(3):雪花算法生成id(可兼容数值型与字符串型)
    //  ASSIGN_UUID(4):以UUID生成算法作为id生成策略
    //  0 | 00100110111011010101100001101010011000110 | 1000 | 1001 | 000000000010
    // 占位符：0           时间戳（41）                      机器码（5+5）    序列号（12）
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String email;
    @TableField(value = "create_time",select = false)
    //NOTE 数据库中字段"create_time"与属性createTime进行匹配
    //NOTE select * from ...中的*不包含该属性
    private LocalDateTime createTime;
    @TableField(exist = false)
    //NOTE 不再数据库的表中查找该字段
    private Integer online;
    //NOTE 逻辑删除：
    //    删除操作业务问题：业务数据从数据库中丢弃，但数据后期可能在别的表中需要使用
    //    解决方案：使用逻辑删除设置是否可用状态字段，删除时设置字段为不可用状态，数据保留在数据库中（但无法通过userDao的查询查到）
    @TableLogic(value = "0",delval = "1")
    private Integer deleted;
    //NOTE 乐观锁（应对两个不同的持久化层对数据库中同一数据进行操作的并发问题）
    @Version
    private Integer version;

//    public User() {
//    }
//
//    public User(Integer id, String username, String password, String name, Integer age,
//                String gender, String phone, String email, LocalDateTime createTime) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//        this.phone = phone;
//        this.email = email;
//        this.createTime = createTime;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public LocalDateTime getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(LocalDateTime createTime) {
//        this.createTime = createTime;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", gender='" + gender + '\'' +
//                ", phone='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                ", createTime=" + createTime +
//                '}';
//    }
}
