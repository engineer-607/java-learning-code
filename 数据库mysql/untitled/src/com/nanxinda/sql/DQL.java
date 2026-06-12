package com.nanxinda.sql;

public class DQL {
    /*
    DQL-语法
    select 字段列表
    from 表名列表
    where 条件列表
    group by 分组字段列表
    having 分组后条件列表
    order by 排序字段列表
    limit 分页参数
     */
    /*
    DQL-基本查询
    1.查询多个字段
    select 字段1,字段2...from 表名;
    select*from 表名;
    2.设置别名
    select 字段1[as 别名1],[as 别名2]...from 表名;
    3.去除重复记录
    select distinct 字段列表 from 表名;
     */
    /*
    DQL-条件查询
    1.语法
    select 字段列表 from 表名 where 条件列表;
    2.条件
    >               : 大于
    >=              : 大于等于
    <               : 小于
    <=              : 小于等于
    =               : 等于
    <> 或 !=        : 不等于
    BETWEEN...AND... : 在某个范围之内 (含最小值、最大值)
    IN(...)         : 在 IN 之后的列表中的值，多选一
    LIKE 占位符      : 模糊匹配 (_ 匹配单个字符，% 匹配任意个字符)
    IS NULL         : 是 NULL
    AND 或 &&       : 并且 (多个条件同时成立)
    OR 或 ||        : 或者 (多个条件任意一个成立)
    NOT 或 !        : 非，不是
     */
    /*
    DQL-聚合函数
    1.介绍：将一列数据作为一个整体，进行纵向计算
    2.常见聚合函数
     函数	  功能
     count	统计数量
     max	最大值
     min	最小值
     avg	平均值
     sum	求和
    3.语法
    select 聚合函数（字段列表） from 表名;
    所有的null值不参与聚合函数的计算
     */
    /*
    DQL-分组查询
    1.语法 select 字段列表 from 表名 [where 条件] group by 分组字段 [having 分组后过滤条件]
    2.where和having的区别
    执行时机不同：where 是分组之后进行过滤，不满足where条件，不参与分组；而having 是分组之后对结果进行过滤
    判断条件不同：where不能对聚合函数进行判断，而having可以
    注意事项：
    1.执行顺序：where>聚合函数>having
    2.分组之后，查询的字段一般为聚合函数和分组函数，查询其他字段无任何意义
     */
    /*
    排序查询
    1.语法
    select 字段列表 from 表名 order by 字段1 排序方式1,字段2 排序方式2;
    2.排序方式
    ASC:升序(默认值)
    DESC:降序
    注意：如果是多字段排序，当第一个字段值相同时，才会根据第二个字段进行排序
     */
    /*
    DQL-分页查询
    select 字段列表 from 表名 limit 起始索引,查询记录数;
    注意：
    1.查询第一页，起始索引从0开始，查询后面的页，起始索引=（查询页码-1）*每页显示记录数
    2.分页查询是数据库的方言，不同的数据库有不同的实现，MySQL是Limit。
    3.如果查询的是第一条数据，起始索引可以省略，直接简写为limit 10。
    4.查询记录数代表每一页的数据量
     */
    /*
    DQL-执行顺序
    1.from 表名列表
    2.where 条件列表
    3.group by 分组字段列表
    4.having 分组后条件列表
    5.select 字段列表
    6.order by 排序字段列表
    7.limit 分页参数
     */
}
