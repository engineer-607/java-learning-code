package com.nanxinda.dao;

import com.nanxinda.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

//NOTE 配置Mapper
@Mapper
/// springboot可以扫描到该接口，然后自动生成自动代理
public interface BookDao {
    @Insert("insert into tbl_book (type,name,description) values(#{type},#{name},#{description})")
    public int save(Book book);
    @Update("update tbl_book set type = #{type}, name = #{name}, description =#{description} where id = #{id}")
    public int update(Book book);
    @Delete("delete from tbl_book where id = #{id}")
    public int delete(Integer id);
    @Select("select * from tbl_book where id =#{id}")
    public Book getById(Integer id);
    @Select("select * from tbl_book")
    public List<Book> getAll();
}
