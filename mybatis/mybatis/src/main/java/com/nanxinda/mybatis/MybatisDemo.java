package com.nanxinda.mybatis;

import com.nanxinda.pogo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        //1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.执行sql
        List<User> list = sqlSession.selectList("com.nanxinda.mapper.UserMapper.selectAll");
        /// 使用Mapper代理方式
        /// 1.定义与SQL文件同名的Mapper接口（UserMapper），并且讲Mapper接口和SQL映射文件放置在同一目录下
        /// UserMapper.xml放在resources\com\nanxinda\mapper\UserMapper.xml，UserMapper接口放在java\com\nanxinda\mapper\UserMapper.java
        /// 二者在项目编译打包后，文件的层次结构完全一致
        /// 2.设置SQL映射文件中的namespace属性为Mapper接口全限定名
        /// 3.在Mapper接口中定义方法，方法名
        for (User user : list) {
            System.out.println(user);
        }
    }
}
 /**
  * MyBatis 核心对象与 JDBC 对应关系对比表
  *
  * | MyBatis 对象        | 对应 JDBC 概念                | 职责描述                                         | 生命周期                    |
  * | :----------------- | :--------------------------- | :---------------------------------------------- | :------------------------- |
  * | SqlSessionFactory  | DataSource / Connection Pool | 工厂级别。负责创建 SqlSession，持有配置等重型信息。     | 整个应用运行期间（单例）      |
  * | SqlSession         | Connection (+ Transaction)   | 会话级别。代表一次数据库连接，负责执行 SQL 及事务管理。 | 请求/线程级（用完即关）      |
  * | Mapper / Executor  | Statement / PreparedStatement| 执行级别。底层真正负责拼接 SQL、设置参数并与数据库交互。 | 随 SqlSession 创建而存在    |
  */
