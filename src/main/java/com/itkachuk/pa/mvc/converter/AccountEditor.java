package com.itkachuk.pa.mvc.converter;

import com.itkachuk.pa.mvc.model.AccountEntity;
import com.itkachuk.pa.mvc.service.AccountService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: itkachuk
 * Date: 12/11/2015 1:16 PM
 */
public class AccountEditor extends PropertyEditorSupport {

    private AccountService accountService;

    public AccountEditor(AccountService accountService) {
        this.accountService = accountService;
    }

    // Converts a String ID to an AccountEntity (when submitting form)
    @Override
    public void setAsText(String id) {
        AccountEntity accountEntity = accountService.getAccountById(Integer.parseInt(id));
        this.setValue(accountEntity);
    }
}
