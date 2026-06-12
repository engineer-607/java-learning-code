package com.nanxinda.sql;

public class Constraint {
/// 数据库约束说明
//1.概念：约束是作用于表中字段上的规则，用于限制存储在表中的数据
//2.目的：保证数据库中数据的正确、有效性和完整性
//3.分类：
// NOT NULL    : 非空约束 —— 限制该字段的数据不能为 null
// UNIQUE      : 唯一约束 —— 保证该字段的所有数据都是唯一、不重复的
// PRIMARY KEY : 主键约束 —— 主键是一行数据的唯一标识，要求非空且唯一
// DEFAULT     : 默认约束 —— 保存数据时，如果未指定该字段的值，则采用默认值
// CHECK       : 检查约束 —— 保证字段值满足某一个条件 (MySQL 8.0.16版本之后)
// FOREIGN KEY : 外键约束 —— 用来让两张表的数据之间建立连接，保证数据的一致性和完整性
//注意：约束是作用域表中字段上的，可以创建表/修改表的时候添加约束

//外键约束：外键用来让两张表建立连接，从而保证数据的一致性和完整性
//语法：
    //create table 表名(
    //      字段名 数据类型,
    //      ...
    //      [constraint][外键名称]foreign key(外键字段名) references 主表(主表列表)
    //    );
    //alter table 表名 add constraint 外键名称 foreign key(外键字段名) references 主表(主表列名);

    //外键约束中的删除/更新行为
    // NO ACTION   : 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有则不允许删除/更新。(与 RESTRICT 一致)
    // RESTRICT    : 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有则不允许删除/更新。(与 NO ACTION 一致)
    // CASCADE     : 当在父表中删除/更新对应记录时，首先检查该记录是否有对应外键，如果有，则也删除/更新外键在子表中的记录。
    // SET NULL    : 当在父表中删除对应记录时，首先检查该记录是否有对应外键，如果有则设置子表中该外键值为 null (要求该外键允许取 null)。
    // SET DEFAULT : 父表有变更时，子表将外键列设置成一个默认的值 (InnoDB 引擎不支持)。

    //alter table 表名 add constraint 外键名称 foreign key(外键字段) references 主表名(主表字段名) on update cascade on delete cascade;



}
