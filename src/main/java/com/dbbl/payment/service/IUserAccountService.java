package com.dbbl.payment.service;

import com.dbbl.payment.dto.UserAccountDto;
import com.dbbl.payment.model.UserAccount;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface IUserAccountService {

    public List<UserAccount> findAll();

    public UserAccount createSystemUser(UserAccountDto userAccountDTO);

    public void deleteSystemUserById(Long id, String loginUser) throws SelfUserOperationException;

    public void disableAdminUser(Long id, String loginUser) throws SelfUserOperationException, UsernameNotFoundException;
}
