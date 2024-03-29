package com.dbbl.payment.service;

import com.dbbl.payment.dto.AccountTransanctionHistoryDto;
import com.dbbl.payment.dto.CustomerDto;
import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.Customer;
import com.dbbl.payment.service.exception.AccountNumberNotFoundException;
import com.dbbl.payment.service.exception.OperationOnDeletedCustomerException;

import java.util.List;

public interface IAccountService {
    public Customer createAccount(CustomerDto customer);
    public List<Account> findAll();
    public Account findAccountInformation(AccountTransanctionHistoryDto dto) throws AccountNumberNotFoundException;

    public Account deactivateOrActivateAccount(Long id) throws AccountNumberNotFoundException, OperationOnDeletedCustomerException;
}
