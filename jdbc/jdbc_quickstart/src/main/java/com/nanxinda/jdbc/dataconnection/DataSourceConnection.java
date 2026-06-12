package com.nanxinda.jdbc.dataconnection;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class DataSourceConnection {
    /*
    数据库连接池简介
    数据库连接池是个容器，负责分配、管理数据库连接(Connection)
    它允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个；
    释放空闲时间超过最大空闲时间的数据库连接来避免因为没有释放数据库连接而引|起的数据库连接遗漏
    好处:
    资源重用
    提升系统响应速度
    避免数据库连接遗漏
     */
    public static void main(String[] args) throws Exception {
        //1.导入maven依赖
        //2.定义配置文件
        //3.加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));

        //4.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //5.获取数据库连接Connection
        Connection connection =  dataSource.getConnection();
        System.out.println(connection);

    }
    ///创建connection这个过程依旧会用到driverClassName=com.mysql.cj.jdbc.Driver，因为这是这个驱动类
    /// 的核心功能：创建数据库连接，其继承父类NonRegisteringDriver，而在父类中有connect方法，用来创建Connection
    ///相比与说Driver是翻译员，说其是传话筒更加贴切，因为其最大的职责是让Java程序能和MySQL通信，比如建 TCP连接
    ///按MySQL协议发数据、接收结果、封装 ResultSet。当然其也做了一些翻译工作，比如将java类型翻译成对应的SQL类型
    ///同样也包括客户端预编译（这个预编译与服务器预编译不同，类似于字符串替换将?替换成对应的内容，但在这个过程会进行很多安全处理，确保避免sql注入），
    ///真正进行SQL语义解析（SQL优化器 、索引分析 、执行计划 、join优化）还是在mysql服务器上执行的。而Driver初始化执行的DriverManager.registerDriver(new Driver());
    ///只是一个初始化工作，让其注册到DriverManager，作为Driver调度中心的DriverManager要管理Driver（比如遍历现有Driver查找url匹配哪个Drvier）
}
