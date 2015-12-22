package com.itkachuk.pa.mvc.controller;

import com.itkachuk.pa.mvc.dao.UserRepository;
import com.itkachuk.pa.mvc.model.AccountEntity;
import com.itkachuk.pa.mvc.service.AccountService;
import com.itkachuk.pa.mvc.utils.AuthUtils;
import com.itkachuk.pa.mvc.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: itkachuk
 * Date: 12/22/2015 10:21 AM
 */
@Controller
@RequestMapping("/admin/account")
public class AccountController {

    private static final String redirectToAccountBaseUrl = "redirect:/admin/account/-1";

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository; // TODO - refactor to service

    @RequestMapping(method = RequestMethod.GET, value = "/{accountId}")
    public String getAccountsList(@PathVariable("accountId") int accountId, Model model) {

        String username = AuthUtils.getLoggedUserName();

        List<AccountEntity> accountsList = accountService.getAccountsList(username);

        AccountEntity account;
        if (accountId == -1) { // just a stub for Spring form
            account = new AccountEntity();
        } else {
            account = accountService.getAccountById(accountId);
        }

        model.addAttribute("accountForm", account);
        model.addAttribute("accountsList", accountsList);
        model.addAttribute("currenciesList", Constants.currencies);
        model.addAttribute("loggedUser", username);
        return "accountsAdmin";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String createNewAccount(@ModelAttribute("accountForm") AccountEntity account) {

        account.setUser(userRepository.getUserByName(AuthUtils.getLoggedUserName()));
        accountService.createNewAccount(account);
        return redirectToAccountBaseUrl;
    }


//    @RequestMapping(method = RequestMethod.GET, value = "/update/{accountId}")
//    public String showUpdateAccountForm(@PathVariable("accountId") int id, Model model) {
//
//        AccountEntity account = accountService.getAccountById(id);
//        model.addAttribute("accountForm", account);
//        model.addAttribute("accountId", id);
//        return "redirect:/admin/account/update/" + id;
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{accountId}")
    public String updateAccount(@PathVariable("accountId") int id, @ModelAttribute("accountForm") AccountEntity account) {

        account.setId(id); // account object has lost it's fields, not reflected in the form, when passed trough the Spring MVC, so we need to add them manually
        account.setUser(userRepository.getUserByName(AuthUtils.getLoggedUserName())); // TODO - need to check
        accountService.updateAccount(account);
        return redirectToAccountBaseUrl;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{accountId}")
    @Transactional
    public String deleteAccount(@PathVariable("accountId") int id) {

        // TODO - delete all relevant records first

        accountService.deleteAccount(id);
        return redirectToAccountBaseUrl;
    }
}
