package com.nanxinda.login.service;

import com.nanxinda.login.dto.LoginFormDTO;
import com.nanxinda.system.dto.Result;

public interface UserService {

    public Result sendCode(String type,String phone);

    public Result login(LoginFormDTO loginFormDTO);
}
