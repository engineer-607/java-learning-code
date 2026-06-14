package com.nanxinda.jdbc.api;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementDetail {
    /*
    Statement
    *Statement作用：
    1. 执行SQL语句
    *执行SQL语句
    int executeUpdate(sqI)：执行DML、DDL语句(DML数据操作、DDL数据库定义)
    返回值：(1)DML语句影响的行数(2)DDL语句执行后，执行成功也可能返回0
    ResultSet executeQuery(sql)：执行DQL语句
    返回值：ResultSet 结果集对象
     */
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/itcast";
        String username = "ubuntu";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "create database nanxinda";
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(sql);
        System.out.println(count);
        statement.close();
        connection.close();
    }
}
