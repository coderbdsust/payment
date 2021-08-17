package com.dbbl.payment.service.exception;

public class SelfAccountTransferException extends Throwable {
    public SelfAccountTransferException(String s) {
        super(s);
    }
}
