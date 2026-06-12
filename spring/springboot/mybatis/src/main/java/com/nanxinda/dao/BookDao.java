package com.nanxinda.dao;

import com.nanxinda.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
/// springboot可以扫描到该接口，然后自动生成自动代理
public interface BookDao {
    @Select("select * from tbl_book where id = #{id}")
    public Book getById(Integer id);
}
