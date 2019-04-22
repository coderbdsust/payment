package com.dbbl.payment.controller;

import com.dbbl.payment.constants.MessageType;
import com.dbbl.payment.dto.SendMoneyDto;
import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.AccountTransanctionHistory;
import com.dbbl.payment.service.AccountService;
import com.dbbl.payment.service.AccountTransanctionService;
import com.dbbl.payment.service.exception.AccountNumberNotFoundException;
import com.dbbl.payment.service.exception.InSufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SendMoneyController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountTransanctionService accountTransanctionService;

    @GetMapping("/account/transanction/send-money/create")
    public String createSendMoneyPage(Model model){
        model.addAttribute("sendMoneyDto", new SendMoneyDto());
        return "send-money/send-money-create";
    }

    @PostMapping("/account/transanction/send-money/details")
    public String confirmSendMoneyPage(@Valid SendMoneyDto dto, BindingResult errors, Model model, RedirectAttributes redirectAttributes){
        if(errors.hasErrors()){
            return "send-money/send-money-create";
        }

        try{
            Account fromAccount = accountService.findAccountInformation(dto.getFromAccount());
            Account toAccount = accountService.findAccountInformation(dto.getToAccount());
            if(!fromAccount.isEnabled() || !toAccount.isEnabled()) throw  new AccountNumberNotFoundException("Account not enable for transanction");
            model.addAttribute("fromAccount", fromAccount);
            model.addAttribute("toAccount", toAccount);
            model.addAttribute("sendMoneyDto", dto);
        }catch(AccountNumberNotFoundException e){
            redirectAttributes.addAttribute("messageType", MessageType.ERROR);
            redirectAttributes.addAttribute("message",e.getLocalizedMessage());
            return "redirect:/account/transanction/send-money/create";
        }

        return "send-money/send-money-confirm";
    }

    @PostMapping("/account/transanction/send-money/confirm")
    public String confirmSendMoneyPage(@Valid SendMoneyDto dto, RedirectAttributes redirectAttributes){
        try{
            AccountTransanctionHistory accountTransanctionHistory = accountTransanctionService.doSendMoneyTransanction(dto);
        }catch (AccountNumberNotFoundException e){
            redirectAttributes.addAttribute("messageType", MessageType.ERROR);
            redirectAttributes.addAttribute("message",e.getLocalizedMessage());
            return "redirect:/account/transanction/send-money/create";
        }catch(InSufficientBalanceException ex){
            redirectAttributes.addAttribute("messageType", MessageType.ERROR);
            redirectAttributes.addAttribute("message","Sender doesn't have enough balance for this transanction");
            return "redirect:/account/transanction/send-money/create";
        }
        redirectAttributes.addAttribute("accountId", dto.getFromAccount().getAccountId());
        return "redirect:/account/transanction/history/{accountId}";
    }
}
