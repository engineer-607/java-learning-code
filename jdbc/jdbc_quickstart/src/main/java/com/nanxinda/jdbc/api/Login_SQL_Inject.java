package com.nanxinda.jdbc.api;

import java.sql.*;

/// SQL注入现象：
/// 通过输入特定的密码达到数据库没有相应的账号和密码也可以进入服务器内部达到攻击服务器的目的
/// 原理：问题出在这个传入的sql语句是字符串拼接而成的，别人就可以传入根据sql语句传入特定的语句达到破解的效果
public class Login_SQL_Inject {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/itcast";
        String username = "ubuntu";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        String name = "zhangsan";
        String pwd = "' or '1' = '1";
        Statement statement = connection.createStatement();
        String sql = "select * from tb_stu_user where username = '"+name+"'and password ='"+pwd+"'";
        System.out.println(sql);
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }
        result.close();
        statement.close();
        connection.close();

    }
}
