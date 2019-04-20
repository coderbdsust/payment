package com.dbbl.payment.service;

import com.dbbl.payment.dto.AccountTransanctionHistoryDto;
import com.dbbl.payment.dto.SendMoneyDto;
import com.dbbl.payment.model.AccountTransanctionHistory;
import com.dbbl.payment.service.exception.AccountNumberNotFoundException;
import com.dbbl.payment.service.exception.InSufficientBalanceException;

import java.util.List;

public interface IAccountTransanctionService {
    public AccountTransanctionHistory doDepositedTransanction(AccountTransanctionHistoryDto dto) throws AccountNumberNotFoundException;
    public List<AccountTransanctionHistory> findAllByAccountId(Long accountId) throws AccountNumberNotFoundException;
    public AccountTransanctionHistory doSendMoneyTransanction(SendMoneyDto dto)throws AccountNumberNotFoundException, InSufficientBalanceException;
}
