package com.nanxinda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC概念：
 * • JDBC 就是使用Java语言操作关系型数据库的一套API
 * • 全称：(Java Data Base Connectivity )Java 数据库连接
 * JDBC本质：
 * • 官方(sun公司）定义的一套操作所有关系型数据库的规则，即接口
 * • 各个数据库厂商去实现这套接口，提供数据库驱动jar包
 * • 我们可以使用这套接口(JDBC）编程，真正执行的代码是驱动jar包中的实现类
 * JDBC好处：
 * •各数据库厂商使用相同的接口，Java代码不需要针对不同数据库分别开发
 * 可随时替换底层数据库，访问数据库的Java代码基本不变
 */
public class JDBCDemo
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        //1.注册驱动(现在不需要写，因为SPI（SPI本质让框架/程序自动发现接口实现类的机制）会自动为DriverManager注册com.mysql.jdbc.Driver
        //驱动类)
        Class.forName("com.mysql.jdbc.Driver");//这行代码本质是让jvm进行
        //com.mysql.jdbc.Driver的类加载
        //2.获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/itcast";
        String username = "ubuntu";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        //细节：
        //*如果连接的是本机mysql服务器，并且mysql服务器默认端口是3306，则url可以简写为jdbc:mysql:///数据库名称?参数键值对
        //*配置useSSL=false参数，禁用安全连接方式，解决警告提示
        //3.定义sql
        String sql = "update tb_user set age = 18 where id = 20";
        String sql1 = "update tb_user set phone = 18114636782 where id = 3";
        //4.获取执行sql的对象Statement
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();

        try {
            ///开启事务
            connection.setAutoCommit(false);
            //5.执行sql
            int count = statement.executeUpdate(sql);
            int count1 = statement1.executeUpdate(sql1);

            //6.处理结果
            System.out.println(count);
            System.out.println(count1);
            /// 提交事务
            connection.commit();
        } catch (SQLException e) {
            /// 回滚事务
           connection.rollback();
            throw new RuntimeException(e);
        }


        //7.释放资源
        statement.close();
        connection.close();



    }
}
