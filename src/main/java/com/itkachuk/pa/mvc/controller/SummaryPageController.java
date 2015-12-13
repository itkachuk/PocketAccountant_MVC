package com.itkachuk.pa.mvc.controller;

import com.itkachuk.pa.mvc.converter.AccountEditor;
import com.itkachuk.pa.mvc.converter.CategoryEditor;
import com.itkachuk.pa.mvc.converter.PABindingInitializer;
import com.itkachuk.pa.mvc.model.AccountEntity;
import com.itkachuk.pa.mvc.model.CategoryEntity;
import com.itkachuk.pa.mvc.model.RecordEntity;
import com.itkachuk.pa.mvc.service.AccountService;
import com.itkachuk.pa.mvc.service.CategoryService;
import com.itkachuk.pa.mvc.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Ваня on 09.12.2015.
 */
@Controller
@RequestMapping("/summary")
public class SummaryPageController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PABindingInitializer bindingInitializer;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        bindingInitializer.initBinder(binder, null);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllSummaryPageData(Model model) {

        AccountEntity defaultAccount = accountService.getDefaultAccount();
        model.addAttribute("defaultAccount", defaultAccount);

        List<AccountEntity> accountsList = accountService.getAccountsList();
        model.addAttribute("accountsList", accountsList);

        List<CategoryEntity> categoriesList = categoryService.getCategoriesList();
        model.addAttribute("categoriesList", categoriesList);

        List<RecordEntity> recordsList = recordService.getRecordsListByAccountId(defaultAccount.getId(), 20); // TODO - replace row limit with parameter
        model.addAttribute("recordsList", recordsList);

        long[] summaryAmounts = recordService.calculateAmounts(defaultAccount.getId()); // TODO - take account id from request
        model.addAttribute("summaryAmounts", summaryAmounts);

        RecordEntity record = new RecordEntity();
        model.addAttribute("recordForm", record);

        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/record/add")
    public String addNewRecordFromSummaryPage(@ModelAttribute("recordForm") RecordEntity recordEntity) {

        recordEntity.setTimestamp(System.currentTimeMillis());
        recordService.createNewRecord(recordEntity);
        return "redirect:/summary";

    }
}
