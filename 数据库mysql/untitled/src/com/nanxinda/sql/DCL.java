package com.nanxinda.sql;

public class DCL {
    /*
    DCL-管理用户
    1.查询用户
    use mysql;
    select * from user;
    2.创建用户
    create user '用户名'@'主机名' identified by '密码';（如果主机名想为任意主机，可以用%）
    3.修改用户密码
    alter user '用户名'@'主机名' identified with caching_sha2_password by '新密码';
    4.删除用户
    drop user '用户名'@'主机名';
    注意：
    1.主机名可以使用%通配
    2.这类SQL开发人员操作的比较少，主要是DBA（数据库管理员）使用。
     */
    /*
---------------------------------------------------------
| MySQL 常用权限说明表                                    |
---------------------------------------------------------
| 权限                  | 说明                          |
---------------------------------------------------------
| ALL, ALL PRIVILEGES  | 所有权限                       |
| SELECT               | 查询数据                       |
| INSERT               | 插入数据                       |
| UPDATE               | 修改数据                       |
| DELETE               | 删除数据                       |
| ALTER                | 修改表                         |
| DROP                 | 删除数据库/表/视图               |
| CREATE               | 创建数据库/表                   |
---------------------------------------------------------
1.查询权限
show grants for '用户名'@'主机名';
2.授予权限
grant 权限列表 on 数据库名、表名 to '用户名'@'主机名';
3.撤销权限
remove 权限列表 on 数据库名、表名 from '用户名'@'主机名';
*/
}
