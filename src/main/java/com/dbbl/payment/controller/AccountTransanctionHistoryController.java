package com.dbbl.payment.controller;

import com.dbbl.payment.dto.AccountTransanctionHistoryDto;
import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.AccountTransanctionHistory;
import com.dbbl.payment.service.AccountNumberNotFoundException;
import com.dbbl.payment.service.AccountService;
import com.dbbl.payment.service.AccountTransanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AccountTransanctionHistoryController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountTransanctionService accountTransanctionService;

    @GetMapping("/account/transanction/history/{accountId}")
    public String transanctionHistory(@PathVariable Long accountId, Model model){
        try {
            List<AccountTransanctionHistory> transanctionHistoryList = accountTransanctionService.findAllByAccountId(accountId);
            model.addAttribute("accountTransanctionHistories", transanctionHistoryList);

        }catch (AccountNumberNotFoundException e){
                return "redirect:/account";
        }
        return "account/account-transanction-history";
    }

    @GetMapping("/account/transanction/deposit/open")
    public String openDepositTransanction(Model model){
        model.addAttribute("accountTransanctionHistoryDto", new AccountTransanctionHistoryDto());
        return "deposit/deposit-create";
    }

    @PostMapping("/account/transanction/deposit/details")
    public String confirmDepositTransanction(AccountTransanctionHistoryDto dto, Model model){

        try{
            System.out.println(dto);
            Account account = accountService.findAccountInformation(dto);
            model.addAttribute("account", account);
            model.addAttribute("transanctionDto", dto);
        }catch(AccountNumberNotFoundException e){
            return "redirect:/account/transanction/deposit/open";
        }

        return "deposit/deposit-confirm";
    }

    @PostMapping("/account/transanction/deposit/confirm")
    public String createDepositTransanction(AccountTransanctionHistoryDto dto, RedirectAttributes redirectAttributes){
        try{
            AccountTransanctionHistory  accountTransanctionHistory = accountTransanctionService.doDepositedTransanction(dto);
        }catch (AccountNumberNotFoundException e){
            return "redirect:/account/transanction/deposit/open";
        }
        redirectAttributes.addAttribute("accountId", dto.getAccountId());
        return "redirect:/account/transanction/history/{accountId}";
    }


    @GetMapping("/account/transanction/send-money")
    public String showSendMoneyPage(){
        return "send-money/send-money-create";
    }

}
