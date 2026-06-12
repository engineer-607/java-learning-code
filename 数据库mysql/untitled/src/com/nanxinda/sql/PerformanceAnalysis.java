package com.nanxinda.sql;

public class PerformanceAnalysis {
    /*
    MySQL客户端连接成功之后，通过show[session|global]status 命令可以提供服务器状态信息。通过如下指令
    可以查看当前数据库的insert、update、delete、select的访问频次:
    show global status like "com_______";

    慢查询日志：慢查询日志记录了所有执行时间超过指定参数(long_query_time,单位：秒，默认10秒)
    的所有SQL语句的日志。
    查看慢查询开关是否开启：show variables like 'slow_query_log';

    MySQL的慢查询日志默认没有开启，需要在MySQL的配置文件(/etc/my.cnf)中配置如下信息：
    vi /stc/my.cnf
    开启MySQL慢日志查询开关：
    slow_query_log = 1
    设置慢日志的时间为2秒，SQL语句执行时间超过2秒，就会视为慢查询，记录慢查询日志
    long_query_time = 2
    配置完毕之后，通过以下指令重新启动MySQL服务器进行测试，查看慢日志文件中记录的信息/var/lib/mysql/localhost-slow.log。

    profile详情：
    show profiles 能够在做SQL优化时帮助我们了解时间都耗费到哪里去了。通过have_profiling参数，能够看到当前
    MySQL是否支持
    profile操作：
    select @@have_profiling;
    默认profiling是关闭的，可以通过set语句在session/global级别开启profiling:
    set profiling=1;
    执行一系列的业务SQL的操作，然后通过如下指令查看指令的执行耗时：
    查看每一条SQL的耗时基本情况：
    show profiles;
    查看指定query_id的SQL语句各个阶段的耗时情况
    show profile for query_id;
    查看指定query_id的SQL语句CPU使用情况
    show profile cpu for query_id;

    explain执行计划
    explain或者desc命令获取MySQL如何执行select语句的信息，包括在select语句执行过程中如何连接和连接的顺序
    语法：直接在select语句之前加上关键字explain/desc
    explain select 字段列表 from 表名 where 条件;
+----+-------------+---------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
| id | select_type | table   | partitions | type  | possible_keys | key     | key_len | ref   | rows | filtered | Extra |
+----+-------------+---------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
|  1 | SIMPLE      | tb_user | NULL       | const | PRIMARY       | PRIMARY | 4       | const |    1 |   100.00 | NULL  |
+----+-------------+---------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
explain执行计划各字段含义：
1.id:select查询的序列号，表示查询中执行select子句或者是操作表的顺序(id相同，执行顺序从上到下；id不同，值越大，越先执行)
2.select_type:表示select的类型，常见的取值有simple(简单表，即不使用表连接或者子查询)、primary(主查询，即外层的查询)、
union(union中的第二个或者后面的查询语句)、subquery(select/where之后包含了子查询)等
(important)3.type:表示连接类型，性能由好到差的连接类型位null、system(表只有一行（系统表级别，极快）)、
const(单表唯一匹配（理想状态）)、eq_ref(多表连接时的唯一匹配)、range(非唯一索引匹配（非常不错）)、index、all
(important)4.possible_key:显示可能应用在这张表上的索引，一个或多个。
(important)5.key:实际使用的索引，如果位null，则没有使用索引。
(important)6.Key_len:表示索引中使用的字节数，该值为索引字段最大可能长度，并非实际使用长度，在不损失精确性的前提下，长度越短越好。
7.rows:MySQL认为必须要执行查询的行数，在innodb引擎的表中，是一个估计值，可能并不总是准确的。
8.filtered:表示返回结果的行数占所需读取行数的百分比，filtered的值越大越好
     */
    /*
    order by优化：
    using filesort:通过表的索引或全表扫描，读取满足条件的数据行，然后在排序缓冲区sort buffer中完成排序操作
    所有不是通过索引直接返回结果的排序都叫FileSort排序。
    using index:通过有序索引顺序扫描直接返回有序数据，这种情况即为using index，不需要额外排序，操作效率高
    eg.
    #没有创建索引时，根据age, phone进行排序
    explain select id, age, phone from tb_user order by age , phone;（using filesort）
    #创建索引
    create index idx_user_age_phone_aa on tb_user(age, phone);
    #创建索引后，根据age, phone进行升序排序
    explain select id, age, phone from tb_user order by age , phone;（using index）
    #创建索引后，根据age, phone进行降序排序
    explain select id, age, phone from tb_user order by age desc , phone desc;（using index）
    会进行倒叙使用索引
    explain select id, age, phone from tb_user order by age ase, phone desc;(using index;using filesort)
    age会使用索引，但是phone不会，虽然根据排序的规则，当age相同的时候才会对phone进行升序排序，似乎倒叙使用索引也可以using index
    但是这样做会使指针来回跳跃，会导致随机I/O降低性能

    1.根据排序字段建立适合的索引，多字段排序时，也遵循最左前缀法则
    2.尽量使用覆盖索引
    3.多字段排序，一个升序一个降序，此时需要注意联合索引在创建时的规则（ASC/DESC）
    4.如果不可避免的出现filesort，大数据量排序时，可以适当增大缓冲区大小sort_buffer_size(默认256K)

    group by优化
    extra中使用using temporary使用临时表
    #删除掉目前的联合索引
    idx_user_pro_age_stadrop index idx_user_pro_age_sta on tb_user;
    #执行分组操作，根据profession字段分组
    explain select profession, count(*) from tb_user group by profession ;(using temporary)
    #创建索引
    Create index idx_user_pro_age_sta on tb_user(profession, age, status);
    #执行分组操作，根据profession字段分组
    explain select profession, count(*) from tb_user group by profession;(using index)
    #执分组操作，根据profession字段分组
    explain select profession, count(*) from tb_user group by profession, age;(using index)
    反例：
    explain select profession, count(*) from tb_user group by age;(using index,using temporary)
    使用已创建的索引idx_user_pro_age_sta，但是是乱序的，所以需要建立临时表，来进行排序（计算各个专业的总数需要先进性排数）
    节省计数器的内存空间，也不需要哈希表来记录各个专业的人数

    在分组操作时，可以通过索引来提高效率。
    分组操作时，索引的使用也是满足最左前缀法则的。

    limit优化
    优化思路：一般分页查询时，通过创建覆盖索引能够比较好地提高性能，可以通过覆盖索引加子查询形式进行优化
    select s.* from tb_sku s, (select id from tb_sku order by id limit 9000000,10) a where s.id = a.id;

    count优化：
    explain select count(*) from tb_user;
    MyISAM引擎把一个表地总行数存在了磁盘上，因此执行count(*)的时候会直接返回这个数，效率很高；
    InnoDB引擎就麻烦，它执行count(*)的时候，需要把数据一行一行地从引擎里面读出来，然后累计计数
    优化思路：自己计数
    count的几种用法
    count()是个聚合函数，对于返回的结果集，一地判断，如果count函数的参数不是NULL，累计值就加1，否则不加，最后
    返回累计值。
    用法：count(*)、count(主键)、count(字段)、count(1)
    对这几种count进行分析：
    count的几种用法
    1.count(主键）
    InnoDB引擎会遍历整张表，把每一行的主键id 值都取出来，返回给服务层。服务层拿到主键后，直接按行进行累加(主键不可能为null)。
    2.count（字段）
    没有notnull约束：InnoDB引擎会遍历整张表把每一行的字段值都取出来，返回给服务层，服务层判断是否为null，不为null，计数累加。
    有not nuLl约束：InnoDB引擎会遍历整张表把每一的字段值都取出来，返回给服务层，直接按行进累加。
    3.count(1)
    InnoDB引擎遍历整张表，但不取值。服务层对于返回的每一，放一个数字“进去，直接按进累加。
    4.count(*)
    InnoDB引擎并不会把全部字段取出来，而是专门做了优化，不取值，服务层直接按进累加。

    按照效率排序的话，count(字段)<count(主键id)<count(1)≈count(*)，所以尽量使用count(*)。

    update 优化：
    update student set no ='2000100100' where id = 1 ;
    update student set no ='2000100105' where name='韦一笑";
    如果这个name没有加索引，手动用事务去添加，并且还未提交的时候去更新第一条语句，行索引会升级为表索引
    即第一条语句的执行会堵塞
    原因：InnoDB 的行锁（Record Lock）实际上是加在索引记录上的，而不是加在整行数据上。
    有索引时： 当你执行 update ... where name = '韦一笑'，
    如果 name 有索引，MySQL 能够通过索引定位到那几行特定的记录，并只对这些索引项加锁。
    无索引时： 如果 name 没有索引，MySQL 无法通过索引快速定位。
    为了保证事务的隔离性（防止其他事务在此时修改数据导致数据不一致），
    MySQL 只能进行全表扫描。在扫描过程中，它会给每一条记录都加上行锁。

     */
}
