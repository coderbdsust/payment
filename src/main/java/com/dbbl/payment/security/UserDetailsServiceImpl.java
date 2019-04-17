package com.dbbl.payment.security;

import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.repository.UserAccountRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.slf4j.Logger;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserAccountRepository userAccountRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("userService");
        System.out.println(username);
        UserAccount user = userAccountRepository.getUserAccountByEmail(username);
        System.out.println(user);
        System.out.println("User retrieved");
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        log.info("loadUserByUsername() : {}", username);
        return new SystemUser(user);
    }
}
