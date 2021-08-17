package com.dbbl.payment.controller;

import com.dbbl.payment.constants.MessageType;
import com.dbbl.payment.dto.AccountTransanctionHistoryDto;
import com.dbbl.payment.dto.SendMoneyDto;
import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.AccountTransanctionHistory;
import com.dbbl.payment.service.exception.AccountNumberNotFoundException;
import com.dbbl.payment.service.AccountService;
import com.dbbl.payment.service.AccountTransanctionService;
import com.dbbl.payment.service.exception.InSufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class AccountTransanctionHistoryController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountTransanctionService accountTransanctionService;

    @GetMapping("/account/transanction/history/{accountId}")
    public String transanctionHistory(@PathVariable Long accountId, Model model, RedirectAttributes redirectAttributes){
        try {
            List<AccountTransanctionHistory> transanctionHistoryList = accountTransanctionService.findAllByAccountId(accountId);

            model.addAttribute("accountTransanctionHistories", transanctionHistoryList);

        }catch (AccountNumberNotFoundException e){
            redirectAttributes.addAttribute("messageType", MessageType.WARNING);
            redirectAttributes.addAttribute("message",e.getLocalizedMessage());
            return "redirect:/account";
        }
        return "pages/account/account-transanction-history";
    }

}
