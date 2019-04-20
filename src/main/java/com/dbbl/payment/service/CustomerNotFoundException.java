package com.dbbl.payment.service;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException(String s) {
        super(s);
    }
}
