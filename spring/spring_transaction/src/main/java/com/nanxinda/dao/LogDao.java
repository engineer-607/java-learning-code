package com.nanxinda.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface LogDao {
    @Insert("insert into tbl_log (info,createDate) values (#{info},now())")
    void log(String info);
}
