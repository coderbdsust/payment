package com.dbbl.payment.controller;

import com.dbbl.payment.constants.MessageType;
import com.dbbl.payment.dto.SendMoneyDto;
import com.dbbl.payment.model.AccountTransanctionHistory;
import com.dbbl.payment.service.AccountService;
import com.dbbl.payment.service.AccountTransanctionService;
import com.dbbl.payment.service.exception.AccountNumberNotFoundException;
import com.dbbl.payment.service.exception.InSufficientBalanceException;
import com.dbbl.payment.service.exception.SelfAccountTransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class SendMoneyRestController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountTransanctionService accountTransanctionService;

    @PostMapping("/account/transanction/rest/send-money/confirm")
    @ResponseBody
    public ResponseEntity confirmSendMoneyPage(@Valid SendMoneyDto dto) {
        try {
            AccountTransanctionHistory accountTransanctionHistory = accountTransanctionService.doSendMoneyTransanction(dto);
            return ResponseEntity.ok().body(accountTransanctionHistory);
        } catch (AccountNumberNotFoundException | InSufficientBalanceException | SelfAccountTransferException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e);
        }
    }
}
