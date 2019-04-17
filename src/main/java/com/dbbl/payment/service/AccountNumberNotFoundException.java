package com.dbbl.payment.service;

public class AccountNumberNotFoundException extends Throwable {
    public AccountNumberNotFoundException(String s) {
        super(s);
    }
}
