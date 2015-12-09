package com.itkachuk.pa.mvc.service;

import com.itkachuk.pa.mvc.dao.AccountRepository;
import com.itkachuk.pa.mvc.model.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ваня on 09.12.2015.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity getDefaultAccount() {
        // TODO: will return default account, saved in app properties
        return accountRepository.getAccountById(1); // temp
    }

    @Transactional
    public List<AccountEntity> getAccountsList() {
        return accountRepository.getAccountsList();
    }
}
