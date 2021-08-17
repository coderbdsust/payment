package com.dbbl.payment.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class SendMoneyDto {
    @Valid
    private AccountTransanctionHistoryDto fromAccount;
    @Valid
    private AccountTransanctionHistoryDto toAccount;
    @Digits(integer = 14, fraction = 2, message = "Invalid amount")
    @NotNull(message = "Invalid amount")
    private Float sendingAmount;

    public AccountTransanctionHistoryDto getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(AccountTransanctionHistoryDto fromAccount) {
        this.fromAccount = fromAccount;
    }

    public AccountTransanctionHistoryDto getToAccount() {
        return toAccount;
    }

    public void setToAccount(AccountTransanctionHistoryDto toAccount) {
        this.toAccount = toAccount;
    }

    public Float getSendingAmount() {
        return sendingAmount;
    }

    public void setSendingAmount(Float sendingAmount) {
        this.sendingAmount = sendingAmount;
    }

    @Override
    public String toString() {
        return "SendMoneyDto{" +
                "fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", sendingAmount=" + sendingAmount +
                '}';
    }
}
