package com.nanxinda.sql;

public class IndexGrammar {
    /*
    索引操作语法
    1.创建索引
    create[unique | fulltext] index index_name on table_name(index_col_name...);
    2.查看索引
    show index from table_name;
    3.删除索引
    drop index index_name on table_name;（这只能删除普通索引）
    如果要删除现在的主键索引，需要通过修改表结构ALTER TABLE tb_user DROP PRIMARY KEY;
     */
}
