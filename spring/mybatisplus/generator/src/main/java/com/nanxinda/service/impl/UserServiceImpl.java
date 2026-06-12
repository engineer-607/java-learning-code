package com.nanxinda.service.impl;

import com.nanxinda.domain.User;
import com.nanxinda.dao.UserMapper;
import com.nanxinda.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 庞绍祥
 * @since 2026-06-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
