package com.nanxinda.service;

import org.springframework.transaction.annotation.Transactional;
/// 1.对需要进行事务处理的方法所处的接口添加注解式事务

public interface AccountService {
    /// 事务管理员：发起事务方，在Spring中通常指代业务层开启事物的方法
    @Transactional(rollbackFor = {NullPointerException.class})
    void transfer(String out,String in,Double money);
}
// * ==================================================================================
// * 事务相关配置 (Transaction Configurations)
// * ==================================================================================
// * 属性 (Attribute)         | 作用 (Description)                    | 示例 (Example)
// * -------------------------+-------------------------------------+-------------------------------------------------
// * readOnly                 | 设置是否为只读事务                     | readOnly = true (只读事务)
// * |                                     |
// * timeout                  | 设置事务超时时间                       | timeout = -1 (永超时)
// * |                                     |
// * rollbackFor              | 设置事务回滚异常 (class)               | rollbackFor = {NullPointerException.class}
// * |                                     |
// * rollbackForClassName     | 设置事务回滚异常 (String)              | 同上格式为字符串
// * |                                     |
// * noRollbackFor            | 设置事务不回滚异常 (class)             | noRollbackFor = {NullPointerException.class}
// * |                                     |
// * noRollbackForClassName   | 设置事务不回滚异常 (String)            | 同上格式为字符串
// * |                                     |
// * propagation              | 设置事务传播行为                       | ......
// * ----------------------------------------------------------------------------------
// */
/// 事务传播行为：事务协调员对事务管理员所携带事务的处理态度
