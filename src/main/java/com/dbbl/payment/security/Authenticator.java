package com.dbbl.payment.security;

import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.repository.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class Authenticator implements AuthenticationProvider {
	private static final Logger log = LoggerFactory.getLogger(Authenticator.class);

	@Autowired
	UserAccountRepository userAccountRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("authenticate:/ ");

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserAccount userAccount = userAccountRepository.getUserAccountByEmail(username);

		log.info("Retrieved user: " + userAccount);

		if (userAccount == null) {
			throw new UsernameNotFoundException("Username is invalid");
		}

		if (!userAccount.getPassword().equals(password)) {
			throw new BadCredentialsException("Password is incorrect");
		}

		WebSystemUser systemUser = new WebSystemUser(userAccount);

		return new UsernamePasswordAuthenticationToken(username, password, systemUser.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
