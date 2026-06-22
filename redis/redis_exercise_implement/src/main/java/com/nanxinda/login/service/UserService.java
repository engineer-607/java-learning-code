package com.nanxinda.login.service;

import com.nanxinda.login.dto.LoginFormDTO;
import com.nanxinda.login.dto.Result;
import jakarta.servlet.http.HttpSession;

public interface UserService {

    public Result sendCode(String type,String phone);

    public Result login(LoginFormDTO loginFormDTO);
}
