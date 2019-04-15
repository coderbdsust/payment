package com.dbbl.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountTransanctionHistoryController {

    @GetMapping("/account/transanction/history")
    public String transanctionHistory(){
        return "account/account-transanction-history";
    }
}
