package com.dbbl.payment.controller;

import com.dbbl.payment.dto.CustomerDto;
import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.Customer;
import com.dbbl.payment.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AccountController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    AccountService accountService;

    @GetMapping("/account/create")
    public String goCreateAccountPage(Model model){
        model.addAttribute("customerDto", new CustomerDto());
        return "account/account-create";
    }

    @PostMapping("/account/create")
    public String createAccount(@Valid CustomerDto customer, BindingResult errors){

        if(errors.hasErrors()){
            return "account/account-create";
        }
        Customer openCustomer = accountService.createAccount(customer);
        return "redirect:/account";
    }

    @GetMapping("/account")
    public String showAccounts(Model models){
        List<Account> accountList = accountService.findAll();
        models.addAttribute("accounts", accountList);
        return "account/account-list";
    }


}
