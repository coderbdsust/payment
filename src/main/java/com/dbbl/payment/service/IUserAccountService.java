package com.dbbl.payment.service;

import com.dbbl.payment.dto.UserAccountDto;
import com.dbbl.payment.model.UserAccount;

import java.util.List;


public interface IUserAccountService {

    public List<UserAccount> findAll();

    public UserAccount createSystemUser(UserAccountDto userAccountDTO);

    public void deleteSystemUserById(Long id);

    public void disableAdminUser(Long id);
}
