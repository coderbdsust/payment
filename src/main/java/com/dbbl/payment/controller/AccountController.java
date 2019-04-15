package com.dbbl.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/account/create")
    public String createAccount(){
        return "account/account-create";
    }

    @GetMapping("/account/deposit")
    public String showDepositPage(){
        return "deposit/deposit-create";
    }

    @GetMapping("/account/send-money")
    public String showSendMoneyPage(){
        return "send-money/send-money-create";
    }

    @GetMapping("/account")
    public String showAccounts(){
        return "account/account-list";
    }


}
