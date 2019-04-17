package com.dbbl.payment.security;

import com.dbbl.payment.model.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.stream.Collectors;

public class SystemUser implements UserDetails {

    private UserAccount user;

    public SystemUser(UserAccount user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getUserRoles().stream().map(authority ->
                new SimpleGrantedAuthority(authority.getRoleName().toString())).collect(Collectors.toList());
    }

    public Long getId(){
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

    public SystemUser getUserDetails(){
        return this;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "user=" + user +
                '}';
    }
}
