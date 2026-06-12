package com.nanxinda.sql;

public class IndexUse {
    /*
    索引使用
    1.最左前缀法则：如果索引了多列（联合索引），要遵守最左前缀法则。最左前缀法则指的是查询从索引的最左列
    开始，并且不跳过索引中的列。
    eg.如果联合索引中包含profession,age,status,那么1，2，3都可以按照联合索引来查，4，5无法用
    而6是专业使用索引，而后面索引失效，采用专业为软件工程的局部全查
       1.explain select * from tb_user where profession='软件工程'and age=31and status='0';
       2.explain select * from tb_user where profession='软件工程'and age=31;
       3.explain select * from tb_user where profession='软件工程';
       4.explain select * from tb_user where age =31 and status ='0';
       5.explain select * from tb_user where status ='0';
       6.explain select * from tb_user where profession='软件工程' and status='0';
     */
    /// 类比：联合索引就像一把“多节梯子”，必须从第一级（最左列）开始爬。如果你中间拆掉了一级，后面那一级就够不到了。
    /// 易错点：最左并不是第一列一定要放在最左边，而是最左列一定要存在，而顺序可以随便放，但是各个列的存在情况得按
    /// 联合索引的顺序存在，比如（a，b，c），查得时候可以c-b-a,b-c-a等等（SQL优化器会帮忙自动排序），
    /// 也可以a-b,b-a，但是b-c就会失效，a-c（c-a）就会部分失效
    /*
    2.范围查询
    联合索引中，出现范围查询(>,<)，范围查询右侧的列索引失效
    explain select*from tb_user where profession ='软件工程'and age >30 and status='0';（完全失效）
    explain select*from tb_user where profession ='软件工程'and age >=30 and status='0';（部分生效）
     */
    /// 最左前缀法则和范围查询本质都是要求被查询字段在查询当下是有序的，不按联合索引的顺序存在，
    /// 那么所需的查询字段就分散B+树的各个地方，而不是有序的，这时候就需要全表扫描，而B+树在这个时候就失效了

    //索引常见失效的场景：
    // 1.不在索引列上进行运算操作，否则索引将失效
    // explain select * from tb_user where substring(phone,10,2) = '15';（失效）
    // 2.字符串类型字段使用时，不加引号，索引将失效。
    // explain select*from tb_user where profession ='软件工程'and age =31 and status =0;（失效）
    // explain select* from tb_user where phone =17799990015;（失效）
    // 3.模糊查询：如果仅仅是尾部模糊匹配，索引不会失效。如果是头部模糊匹配，索引将失效
    //explain select * from tb_user where profession like '软件%';（有效）
    // explain select * from tb_user where profession like '%工程';（失效）
    // explain select * from tb_user where profession like '%工%';（失效）
    /// 本质逻辑：破坏B+树的有序性，B+树存储的是原始数据，如果进行一定的操作（函数或者匹配最后几位）才能
    /// 得到结果，进而造成全表查询（第二点本质用了隐式转换的函数，也是属于第一类），至于第三类中尾部模糊能够
    /// 成功，本质是最左前缀在发挥作用，B+树能够轻而易举找到开头为软件的数据（类比字典中找A开头很容易，找ing
    /// 结尾就需要翻遍整个表）

    //4.or连接的条件：用or分割开的条件，如果or前的条件中的列有索引，而后面的列没有索引，那么索引都不会被用到。
    //explain select * from tb_user where id = 10 or age = 23;
    //explain select * from tb_user where phone ='17799990017' or age = 23;
    //由于age没有索引，即便id、phone有索引，索引也会失效。所以需要针对age建立索引（因为有没有索引的字段
    //即便有的字段有索引，但终究还是需要全表扫描，所以最终索引就是会失效）
    //5.数据分布影响：如果MySQL评估使用索引比全表更慢，则不使用索引。
    // select * from tb_user where phone >='17799990005';
    // select * from tb_user where phone >='17799990015';
    /// 当走索引的时候数据量太大，回表查询随机I/O的成本超过阈值时，就会放弃通过二级索引-》回表查询这条路
    /// 而是直接在聚焦索引这里全表扫描（随机I/O：二级索引里记录的 id 在主键索引（聚簇索引）的物理分布上可能是不连续的，
    /// 磁头或闪存控制器需要不停地“跳跃”去寻找数据；顺序I/O：顺序 I/O。它沿着聚簇索引最底层的双向链表一路扫过去，
    /// 利用了磁盘预读（Read-ahead）机制，效率在数据量大时反而更高。）
    /// 阈值可以简单理解为‘随机 I/O 带来的寻址延迟 > 顺序扫描全表的吞吐收益’的临界点

    //SQL提示：SQL提示，是优化数据库的一个重要手段，简单来说，就是在SQL语句中加入一些人为的提示来达到优化
    //操作的目的
    //use index:（建议使用）
    //explain select *from tb_user use index(idx_user_pro)where profession ='软件工程';
    //ignore index:（忽略某个索引）
    //explain select * from tb_user ignore index(idx_user_pro)where profession='软件工程';
    //force index:（强制使用
    //explain select*from tb_user force index(idx_user_pro)where profession='软件工程';

    //覆盖索引：不需要回表查询就能找到对应的数据，即二级索引的B+树中包含所要查找的数据
    //前缀索引：当字段类型为字符串（varchar，text等），有时候需要索引很长的字符串，这会让索引
    //变得很大，查询时，浪费大量的磁盘IO，影响查询效率。此时可以只将字符串的一部分前缀，建立索引
    //这样可以大大节约索引空间，从而提高索引效率
    //语法:create index idx_xxxx on table_name(column(n));
    //前缀长度
    //可以根据索引的选择性来决定，而选择性是指不重复的索引值（基数）和数据表的记录总数的比值，索引选择性越高则查询效率越高，
    //唯一索引的选择性是1，这是最好的索引选择性，性能也是最好的。
    //select count(distinct email) / count(*) from tb_user;
    //select count(distinct substring(email,1,5)) / count(*) from tb_user;
    //建立索引：create index idx_email_5 on tb_user (email(5));

    /// 索引设计原则
    /// 1.针对于数据量较大，且查询比较频繁的表建立索引。
    /// 2.针对于常作为查询条件（where）、排序（order by）、分组（group by）操作的字段建索引。
    /// 3.针对于常作为查询条件（where）、排序（order by）、分组（group by）操作的字段建索引。
    /// 尽量选择区分度的列作为索引，尽量建唯索引，区分度越，使索引的效率越。
    /// 4.如果是字符串类型的字段，字段的度较，可以针对于字段的特点，建前缀索引。
    /// 5.尽量使联合索引，减少单列索引，查询时，联合索引很多时候可以覆盖索引，节省存储空间，避免回表，提查询效率。
    /// 6.要控制索引的数量，索引并不是多多益善，索引越多，维护索引结构的代价也就越，会影响增删改的效率。
    /// 7.如果索引列不能存储NULL值，请在创建表时使NOTNULL约束它。当优化器知道每列是否包含NULL值时，
    /// 它可以更好地确定哪个索引最有效地于查询。
}
