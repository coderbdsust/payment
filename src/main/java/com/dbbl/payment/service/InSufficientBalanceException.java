package com.dbbl.payment.service;

public class InSufficientBalanceException extends Throwable {
    public InSufficientBalanceException(String account_number_not_found) {
    }
}
