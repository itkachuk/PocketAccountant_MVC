package com.itkachuk.pa.mvc.converter;

import com.itkachuk.pa.mvc.model.AccountEntity;
import com.itkachuk.pa.mvc.model.CategoryEntity;
import com.itkachuk.pa.mvc.service.AccountService;
import com.itkachuk.pa.mvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * Created with IntelliJ IDEA.
 * User: itkachuk
 * Date: 12/11/2015 2:51 PM
 */
@Component
public class PABindingInitializer implements WebBindingInitializer {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request)
    {
        binder.registerCustomEditor(AccountEntity.class, new AccountEditor(accountService));
        binder.registerCustomEditor(CategoryEntity.class, new CategoryEditor(categoryService));
    }
}
