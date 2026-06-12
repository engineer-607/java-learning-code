package com.nanxinda.sql;
@SuppressWarnings("all")
public class Muti_table_queries {
    /*
    多表关系
    一对多：
    案例：部门与员工的关系
    关系：一个部门对应多个员工，一个员工对应一个部门
    实现：在多的一方建立外键，指向一的一方主键
    多对多：
    案例：学生与课程的关系
    关系：一个学生可以选修多门课程，一门课程也可以供多个学生选择
    实现：建立第三张中间表，中间表至少包含两个外键，分别关联两方主键
    一对一：
    案例：用户与用户详情的关系
    关系：一对一关系，多用于单表拆分，将一张表的基础字段放在一张表中，其中详情字段放在另一张表中，以提升操作效率
    实现：在任意一方加入外键，关联另外一方的主键，并且设置外键为唯一的（unique）
     */
    /*
    笛卡尔积：笛卡尔积是指在数学中，两个集合A集合和B集合的所有组合情况（在多表查询中，需要消除无效的笛卡尔积）
    多表查询分类：
    1）连接查询
    1.内连接：相当于查询A、B交集部分数据
    隐式内连接：select 字段列表 from 表1，表2 where 条件...
    eg.select emp.name,dept.name from emp,dept where dept.id=emp.dept_id;
    显式内连接：select 字段列表 from 表1 [inner] join 表2 on 连接条件...
    select  e.name,d.name  from emp e inner join dept d where e.dept_id = d.id;
    2.外连接：左外连接：查询左表所有数据，以及两张表交际部分数据
             --select 字段列表 from 表1 left[outer] join 表2 on 条件...
             右外连接：查询右表所有数据，以及两张表交集部分数据
             --select 字段列表 from 表1 right[outer] join 表2 on 条件...
    3.子连接：当前表与自身的连接查询，自连必须使用表别名
             select 字段列表 from 表A 别名A join 表B 别名B on 条件...;
             自连接查询，可以是内连接查询，也可以是外连接查询
    4.联合查询：union,union all
             对于union查询，就是多次查询的结果合并起来，形成一个新的查询结果集
             select 字段列表 from 表A...
             union [all]
             select 字段列表 from 表B...;
             前提：字段列表的数量和数据类型得保持一致
    2）子查询
    1.概念：SQL语句中嵌套select语句，称为嵌套查询，又称子查询
    select * from t1 where column1 =(select column from t2);
    子查询外部的语句可以是insert/update/delete/select的任何一个。
    2.根据子查询结果不同，分为：
     2.1 标量子查询(子查询结果为单个值)
     子查询返回的结果是单个值（数字、字符串、日期等），最简单的形式，这种子查询成为标量子查询
     常用的操作符：= <> > >= < <=
     eg.select * from emp where entrydate>(select entrydate from emp where name = '韦一笑');
     2.2 列子查询(子查询结果为一列)
     子查询返回的结果是一列（可以是多行），这种子查询为列子查询
     +-----------+-------------------------------------------------------+
     |  操作符   |                      描  述                           |
     +-----------+-------------------------------------------------------+
     | IN        | 在指定的集合范围之内，多选一                          |
     | NOT IN    | 不在指定的集合范围之内                                |
     | ANY       | 子查询返回列表中，有任意一个满足即可                  |
     | SOME      | 与 ANY 等同，使用 SOME 的地方都可以使用 ANY           |
     | ALL       | 子查询返回列表的所有值都必须满足                      |
     +-----------+-------------------------------------------------------+
     in---select * from emp where dept_id in (select id from dept where dept.name = '销售部' or name = '市场部');
     all---select * from emp where salary>all (select salary from emp where dept_id = (select id from dept where name = '财务部'));
     any---select * from emp where salary>any (select salary from emp where dept_id = (select id from dept where name = '研发部'));
     2.3 行子查询(子查询结果为一行)
     select * from emp where (salary,managerid) = (select salary,managerid from emp where name = '张无忌');
     2.4 表子查询(子查询结果为多行多列)
     select * from emp where (salary,job) in (select salary,job from emp where name = '鹿杖客' or name = '宋远桥');


     */
}
