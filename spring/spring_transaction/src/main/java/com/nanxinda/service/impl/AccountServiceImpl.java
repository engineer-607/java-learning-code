package com.nanxinda.service.impl;

import com.nanxinda.dao.AccountDao;
import com.nanxinda.dao.LogDao;
import com.nanxinda.service.AccountService;
import com.nanxinda.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LogService logService;
    @Override
    public void transfer(String out, String in, Double money) {
        try {
            accountDao.inMoney(out, money);
            accountDao.inMoney(in, money);
        }finally {
            logService.log(out,in,money);
        }
    }
}
