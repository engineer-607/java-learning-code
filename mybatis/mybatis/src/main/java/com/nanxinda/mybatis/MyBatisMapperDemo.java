package com.nanxinda.mybatis;

import com.nanxinda.mapper.UserMapper;
import com.nanxinda.pogo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisMapperDemo {
    public static void main(String[] args) throws IOException {
        //1.加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.执行sql
        //3.1获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        /// 这个返回对象其实是代理对象，这个代理类实现了这个UserMapper接口
        List<User> list = userMapper.selectAll();
        /// 执行这个selectAll时，代理类会将需求转发给InvocationHandler让其执行invoke()
        /// 每一个代理类内部都持有一个 接口InvocationHandler（其被MyBatis 的 MapperProxy实现）
        /// public class $Proxy0 implements UserMapper {
        ///    private InvocationHandler h;
        ///    public List<User> selectAll() {
        ///         return (List<User>) h.invoke(this, "selectAll", null);
        ///    }
        ///}
        /// 接下来这个invoke方法就会去 XML 找对应的 <select id="selectAll">
        for (User user : list) {
            System.out.println(user);
        }
        /// 使用Mapper代理方式
        /// 1.定义与SQL文件同名的Mapper接口（UserMapper），并且讲Mapper接口和SQL映射文件放置在同一目录下
        /// UserMapper.xml放在resources\com\nanxinda\mapper\UserMapper.xml，UserMapper接口放在java\com\nanxinda\mapper\UserMapper.java
        /// 二者在项目编译打包后，文件的层次结构完全一致
        /// 2.设置SQL映射文件中的namespace属性为Mapper接口全限定名
        /// 3.在Mapper接口中定义方法，方法名

        /// 原理分析：sqlSession.getMapper(UserMapper.class)，Mybatis其实利用JDK动态代理
        /// 虽然UserMapper只是一个接口，但是却能调用其selectAll()，本质是Mybatis内部有一个MapperProxy类
        /// 当调用该接口方法时，这个代理类会去拦截调用，在UserMapper.xml里找到对应的SQL，执行JDBC，然后吧结果封装
        /// 成对象返回
    }
}
