package com.nanxinda.dervice.impl;

import com.nanxinda.dao.AccountDao;
import com.nanxinda.dervice.AccountService;
import com.nanxinda.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public void save(Account account) {
           accountDao.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);

    }

    @Override
    public void delete(Integer id) {
        accountDao.delete(id);

    }

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findAll() {
       return accountDao.findAll();
    }
}
