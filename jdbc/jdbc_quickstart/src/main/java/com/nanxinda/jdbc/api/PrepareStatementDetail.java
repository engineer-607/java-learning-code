package com.nanxinda.jdbc.api;

import java.sql.*;

public class PrepareStatementDetail {
    /*
    PreparedStatementPreparedStatement作用:
    1．预编译SQL并执行SQL语句获取 PreparedStatement 对象
    *SQL语句中的参数值，使用?占位符替代
    String sql = "select * from user where username = ? and password = ?";
    //通过Connection对象获取，并传入对应的sql语句
    PreparedStatement prepareStatement = conn.prepareStatement(sql);
    *设置参数值
    PreparedStatement对象：setXxx(参数1，参数2)：给?赋值
    Xxx：数据类型；如 setInt (参数1，参数2)
    参数:
     参数1：?的位置编号，从1 开始
     参数2：?的值
    *执行SQL
    executeUpdate()/ executeQuery()；：不需要再传递sql
     */
    /// 用?避免字符串拼接导致sql注入
    public static void main(String[] args) throws SQLException {

        String username = "ubuntu";
        String password = "123456";
        String url = "jdbc:mysql://127.0.0.1:3306/itcast?useServerPrepStmts=true&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, username, password);
        //定义sql
        String sql = "select * from tb_stu_user where username = ? and  password = ?";
        //获取PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        String name = "zhangsan";
        String pwd = "123456";
        //设置?的值
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,pwd);
        //获取结果
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }
        //关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
    /// 原理分析（服务器编译）
    /// PreparedStatement preparedStatement = connection.prepareStatement(sql);
    /// 在执行这段语句时，sql语句被发送到mysql服务器上进行检查和预编译，但是并没有执行
    /// 真正执行的时候在preparedStatement.executeQuery();这里
    /// 而普通的Statement就不同，在connection.createStatement()和statement.executeQuery(sql)
    /// 之后才会进行检查、编译和执行
    /// 而PreparedStatement相较Statement的优点就在这个预编译
    /// 1.用?解决字符串拼接导致sql注入的发生，更加安全可靠
    /// 2.由于发送的sql语句的参数是由?代替，各个sql语句之间的差异性减少，不少原来因为参数不同而被
    /// 不同的计划执行现在就可以由相同计划执行，达到执行计划缓存的效果（比如插入一千条数据，在以前
    /// Statement可能会生成1000中执行计划，而现在PreparedStatement就会生成一条SQL编译结构，
    /// 不需要每次都去分析insert 表名 字段 索引 执行路径，而这部分恰恰是最消耗时间的部分

    /// 预编译存在两种模式
    /// 1.客户端模拟预编译(客户端模拟预编译)
    /// 预编译过程在客户端执行，最终发给服务器的是select * from tb_stu_user where username = zhangsan and  password = 123456
    /// 优点：这种编译在某些场景更快，因为如果在服务器编译的话，会多一次网络交互、server 要维护 statement（以及其他问题）
    ///      依然可以避免sql注入
    /// 缺点：并不是真正的MySQL server prepare
    /// 2.服务器预编译：具体操作如下：
    /// PreparedStatement预编译功能开启：
    /// useServerPrepStmts=true
    /// 配置MySQL执行日志(重启mysql服务后生效）
    ///log-output=FILE
    /// general-log=1
    /// slow-query-log=1
    /// general_log_file="D:\mysql.log"slow-query-log=1
    /// slow_query_log_file="D:\mysql_slow.log"
    ///long_query_time=2
    /// 至于效果上述有分析

    /// 以上这两种模式并不会同时存在，所以如果客户端预编译完，服务器就会类似普通statement一样在
    /// 执行计划缓存这方面能力较低，但同样的如果使用服务器端预编译，也会有上述额外的代价（网络交互，维持statement）


}
