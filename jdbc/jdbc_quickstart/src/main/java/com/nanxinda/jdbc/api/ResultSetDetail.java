package com.nanxinda.jdbc.api;

import java.sql.*;

public class ResultSetDetail {
    /*
      ResultSet:
    ResultSet(结果集对象)作用:
    1．封装了DQL查询语句的结果
    ResultSet stmt.executeQuery(sql)：执DQL语句，返回ResultSet 对象
    *获取查询结果
    boolean next()：(1)将光标从当前位置向前移动一行(2）判断当前行是否为有效行返回值：
        true：有效行，当前行有数据
        false：无效行，当前行没有数据
    XXX getXxx(参数)：获取数据
    xxx：数据类型；如：int getInt(参数)；String getString(参数)
    参数:
      int：列的编号，从1开始
      String：列的名称
     */
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/itcast";
        String username = "ubuntu";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "select * from tb_user";
        Statement statement = connection.createStatement();
        /// 结果集对象
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.printf("id\tname\t\tphone\n");
        while (resultSet.next()){
            System.out.printf("%d\t%s\t\t%s\n",resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
        }
        statement.close();
        connection.close();
    }
}
