package com.dbbl.payment.constants;

public enum BankAccountType {
    SAVING(101), CURRENT(102), POWER(103);

    private final int accountType;

    BankAccountType(int accountType){
        this.accountType=accountType;
    }

    public int getAccountType(){
        return this.accountType;
    }
}
