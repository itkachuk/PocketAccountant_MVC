package com.itkachuk.pa.mvc.controller;

import com.itkachuk.pa.mvc.model.RecordEntity;
import com.itkachuk.pa.mvc.service.RecordService;
import com.itkachuk.pa.mvc.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Ваня on 21.11.2015.
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String getRecordsList(Model model) {

        String username = AuthUtils.getLoggedUserName();

        List<RecordEntity> recordsList = recordService.getRecordsList(username);

        model.addAttribute("recordsList", recordsList);
        model.addAttribute("loggedUser", username);
        return "index";
    }
}
