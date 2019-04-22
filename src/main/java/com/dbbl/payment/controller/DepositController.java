package com.dbbl.payment.controller;

import com.dbbl.payment.constants.MessageType;
import com.dbbl.payment.dto.AccountTransanctionHistoryDto;
import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.AccountTransanctionHistory;
import com.dbbl.payment.service.AccountService;
import com.dbbl.payment.service.AccountTransanctionService;
import com.dbbl.payment.service.exception.AccountNumberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class DepositController {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountTransanctionService accountTransanctionService;

    @GetMapping("/account/transanction/deposit/open")
    public String openDepositTransanction(Model model){
        model.addAttribute("accountTransanctionHistoryDto", new AccountTransanctionHistoryDto());
        return "deposit/deposit-create";
    }

    @PostMapping("/account/transanction/deposit/details")
    public String confirmDepositTransanction(@Valid AccountTransanctionHistoryDto dto, BindingResult errors, Model model, RedirectAttributes redirectAttributes){
        if(errors.hasErrors()){
            return "deposit/deposit-create";
        }

        try{
            System.out.println(dto);
            Account account = accountService.findAccountInformation(dto);
            if(!account.isEnabled()) throw new AccountNumberNotFoundException("Account is disabled");
            model.addAttribute("account", account);
            model.addAttribute("transanctionDto", dto);
        }catch(AccountNumberNotFoundException e){
            redirectAttributes.addAttribute("messageType", MessageType.WARNING);
            redirectAttributes.addAttribute("message",e.getLocalizedMessage());
            return "redirect:/account/transanction/deposit/open";
        }

        return "deposit/deposit-confirm";
    }

    @PostMapping("/account/transanction/deposit/confirm")
    public String createDepositTransanction(AccountTransanctionHistoryDto dto, RedirectAttributes redirectAttributes){
        try{
            AccountTransanctionHistory accountTransanctionHistory = accountTransanctionService.doDepositedTransanction(dto);
        }catch (AccountNumberNotFoundException e){
            redirectAttributes.addAttribute("messageType", MessageType.WARNING);
            redirectAttributes.addAttribute("message",e.getLocalizedMessage());
            return "redirect:/account/transanction/deposit/open";
        }
        redirectAttributes.addAttribute("accountId", dto.getAccountId());
        return "redirect:/account/transanction/history/{accountId}";
    }

}
