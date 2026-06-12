package com.nanxinda.sql;

public class View {
    /*
    视图
    介绍：是一种虚拟存在的表。视图中的数据并不在数据库中实际存在，行和列数据来自定义视图的查询中使用的表
    ，并且是在使用视图时动态生成的
    通俗的讲，视图只保存了查询的SQL逻辑，不保存查询结果，所以我们在创建视图的时候，主要的工作就落在创建这条
    SQL查询上
    1.创建：create [or replace] view 视图名称(列名列表)] as select 语句[with [ cascaded | local ] option]
    [with [ cascaded | local ] option]表示进行检查插入，更新，删除数据的操作是否符合条件，
    如果不符合条件要求的数据添加进来会报错
    eg.create or replace view stu_v_l as select id,name from tb_sku where id<=10;
    2.查询
    查看创建视图语句：show create view 视图名称;
    查看视图数据:select * from 视图名称......(跟普通查询无区别可以加条件);
    修改 方式一：create [or replace] view 视图名称[(列表名称)] as select 语句 [with [cascaded | local ] check option]
        方式二：alter view 视图名称[(列表名称)] as select 语句 [with [cascaded | local ] check option]
        eg.
        create or replace view stu_v_l as select id,name,no from student where id <= 10;
        alter view stu_v_l as select id,name,no from student where id <= 10;
    删除：drop view [if exists] 视图名称 [视图名称]...

    视图的检查选项
    当使用WITH CHECK OPTION子句创建视图时，MySQL会通过视图检查正在更改的每行，
    例如插入，更新，删除，以使其符合视图的定义。MySQL允许基于另一个视图创建视图，它还会检查依赖视图中的规则以保持一致性。
    为了确定检查的范围，mysql提供了两个选项：CASCADED和LOCAL，默认值为CASCADED。（cascaded表示检查该视图和父视图）
    create view v1 as select id,name from student where id <= 20
    create view v2 as select id , name from v1 where id >= 10 with cascaded check option ;
    往v2中插入、修改、删除数据cascaded就会检查操作是否符合id>=10&&id<=20
    create view v3 as select id , name from v2 where id <=15
    往v3中插入、修改、删除数据cascaded不会检查操作是否符合id<=15,只是检查v2及v2父类的

    local：同样会检查操作是否符合当前视图的要求，也会去向上溯源检查所依赖视图的条件，但是如果溯源的视图虽然有条件
    但是没有定义check option那也不会进行检查（但是cascaded不管有没有定义check option只要有条件限制都需要进行
    检查）

    视图的更新：
    要使视图可更新，视图中的行与基础表中的行之间必须存在一对一的关系。如果视图包含以下任何一项，则该视图不可更新：
    1.聚合函数或窗口函数（SUM()、MIN()、MAX()、COUNT()等）
    2.DISTINCT
    3.GROUP BY
    4.HAVING
    5.UNION或者 UNIONALL

    视图作用
    1.简单：视图不仅可以简化户对数据的理解，也可以简化他们的操作。
    那些被经常使用的查询可以被定义为视图，从使得户不必为以后的操作每次指定全部的条件。
    2.安全：数据库可以授权，但不能授权到数据库特定行和特定的列上。通过视图用户只能查询和修改他们所能见到的数据
    3.数据独立：视图可帮助户屏蔽真实表结构变化带来的影响。（如果原表发生字段的修改，只需要对视图的创建进行
    修改即可，如果没有视图，那么引用该表的java程序都需要进行大规模的改动，而如果引用视图那么就无需改动）
     */
}
