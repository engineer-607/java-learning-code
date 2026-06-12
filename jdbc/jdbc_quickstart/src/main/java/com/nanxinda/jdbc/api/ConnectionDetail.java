package com.nanxinda.jdbc.api;

public class ConnectionDetail {
    /*
    Connection：
    1.普通执行SQL对象
    *普通执行SQL对象
    Statement createStatement();
    *预编译SQL的执行SQL对象：防止SQL注入
     PreparedStatement prepareStatement (sql)
    *执行存储过程的对象
     CallableStatement prepareCall (sql)
     2.事务管理
     JDBC事务管理：Connection接口中定义了3个对应的方法
     *开启事务：setAutoCommit(boolean autoCommit):true为自动提交事务;false为手动提交事务，即为开启事务
     *提交事务：commit()
     *回滚事务：rollkiack()
     */
}
