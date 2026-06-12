package com.nanxinda.dervice;

import com.nanxinda.domain.Account;

import java.util.List;

public interface AccountService {
    public void save(Account account);
    public void update(Account account);
    public void delete(Integer id);
    public Account findById(Integer id);
    public List<Account> findAll();
}
