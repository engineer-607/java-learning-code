package com.nanxinda.login.service.impl;

import com.nanxinda.login.dto.LoginFormDTO;
import com.nanxinda.login.dto.Result;
import com.nanxinda.login.service.UserService;
import com.nanxinda.login.strategy.abstractStrategy.LoginStrategy;
import com.nanxinda.login.strategy.abstractStrategy.SenderCodeStrategy;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, SenderCodeStrategy> map;

    private final Map<String, LoginStrategy> loginMap;
    //NOTE Spring创建UserServiceImpl发现只有一个构造方法，而这个构造方法
    // 需要Map<String,SenderCodeStrategy>map，Spring就会自动在容器中寻找SenderCodeStrategy
    // 类型的Bean,然后组装成一个map，传入构造方法中

    //Map<String, SenderStrategy> map = new HashMap<>();
    //map.put("phone", phoneStrategyBean);
    //map.put("email", emailStrategyBean);
    //UserServiceImpl userService = new UserServiceImpl(map);

    public UserServiceImpl(Map<String, SenderCodeStrategy>map,
                           Map<String,LoginStrategy>loginMap){
        this.map = map;
        this.loginMap = loginMap;
    }

    //发送验证码功能
    @Override
    public Result sendCode(String type,String sendWay) {
        SenderCodeStrategy way = map.get(type);
        return way.sendCode(sendWay);
    }

    @Override
    public Result login(LoginFormDTO loginFormDTO) {
        LoginStrategy  loginStrategy = loginMap.get(loginFormDTO.getType());
        return loginStrategy.loginWay(loginFormDTO.getTypeNum(), loginFormDTO.getCode());
    }
}
