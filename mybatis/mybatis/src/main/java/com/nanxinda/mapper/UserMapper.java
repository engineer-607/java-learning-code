package com.nanxinda.mapper;

import com.nanxinda.pogo.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();
    User selectById(int id);
}
