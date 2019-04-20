package com.dbbl.payment.service;

import com.dbbl.payment.dto.AccountTransanctionHistoryDto;
import com.dbbl.payment.dto.SendMoneyDto;
import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.AccountTransanctionHistory;
import com.dbbl.payment.repository.AccountRepository;
import com.dbbl.payment.repository.AccountTransanctionHistoryRepository;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountTransanctionService implements IAccountTransanctionService{

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountTransanctionHistoryRepository accountTransanctionHistoryRepository;

    @Override
    @Transactional
    public AccountTransanctionHistory doDepositedTransanction(AccountTransanctionHistoryDto dto) throws AccountNumberNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(dto.getAccountId());
        if(accountOptional.isPresent()){

            Account account = accountOptional.get();
            AccountTransanctionHistory accTnxHistory = new AccountTransanctionHistory();
            accTnxHistory.setAccountId(account);
            accTnxHistory.setCreditAmount(dto.getDepositedAmount());
            accTnxHistory.setToAccount(dto.getBranchId()+"."+dto.getBankProductId()+"."+dto.getAccountId());
            accTnxHistory.setTransanctionDate(new Date());
            accTnxHistory.setTransanctionType("Cash");
            accTnxHistory = accountTransanctionHistoryRepository.save(accTnxHistory);
            if(account.getBalance()==null)
                account.setBalance(0.0f);
            account.setBalance(account.getBalance()+dto.getDepositedAmount());
             accountRepository.save(account);
             return accTnxHistory;
        }
         throw new AccountNumberNotFoundException("Account number not found");
    }

    @Override
    public List<AccountTransanctionHistory> findAllByAccountId(Long accountId) throws AccountNumberNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isPresent())
            return accountTransanctionHistoryRepository.findAccountTransanctionHistoriesByAccountId(accountOptional.get());
        throw new AccountNumberNotFoundException("Account number not found");
    }

    @Override
    @Transactional
    public AccountTransanctionHistory doSendMoneyTransanction(SendMoneyDto dto) throws AccountNumberNotFoundException, InSufficientBalanceException {
        Optional<Account> fromAccountOpt = accountRepository.findByIdAndEnabled(dto.getFromAccount().getAccountId(), true);
        Optional<Account> toAccountOpt = accountRepository.findByIdAndEnabled(dto.getToAccount().getAccountId(), true);

        if(fromAccountOpt.isPresent() && toAccountOpt.isPresent()){

            Account fromAccount = fromAccountOpt.get();
            Account toAccount = toAccountOpt.get();

            if(fromAccount.getBalance()-dto.getSendingAmount()<0){
                throw new InSufficientBalanceException("Sender account doesn't have sufficient balance");
            }

            AccountTransanctionHistory fromTnxHistory = new AccountTransanctionHistory();
            fromTnxHistory.setAccountId(fromAccount);
            fromTnxHistory.setDebitAmount(dto.getSendingAmount());
            fromTnxHistory.setFromAccount(fromAccount.getBranchId()+"."+fromAccount.getBankProductId()+"."+fromAccount.getId());
            fromTnxHistory.setToAccount(toAccount.getBranchId()+"."+toAccount.getBankProductId()+"."+toAccount.getId());
            fromTnxHistory.setTransanctionDate(new Date());
            fromTnxHistory.setTransanctionType("Send Money");
            fromTnxHistory = accountTransanctionHistoryRepository.save(fromTnxHistory);

            AccountTransanctionHistory toTnxHistory = new AccountTransanctionHistory();
            toTnxHistory.setAccountId(toAccount);
            toTnxHistory.setCreditAmount(dto.getSendingAmount());
            toTnxHistory.setFromAccount(fromAccount.getBranchId()+"."+fromAccount.getBankProductId()+"."+fromAccount.getId());
            toTnxHistory.setToAccount(toAccount.getBranchId()+"."+toAccount.getBankProductId()+"."+toAccount.getId());
            toTnxHistory.setTransanctionDate(new Date());
            toTnxHistory.setTransanctionType("Send Money");
            toTnxHistory = accountTransanctionHistoryRepository.save(toTnxHistory);

            fromAccount.setBalance(fromAccount.getBalance()-dto.getSendingAmount());
            toAccount.setBalance(toAccount.getBalance()+dto.getSendingAmount());

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            return fromTnxHistory;
        }
        throw new AccountNumberNotFoundException("Account number not found or inactive");
    }
}
