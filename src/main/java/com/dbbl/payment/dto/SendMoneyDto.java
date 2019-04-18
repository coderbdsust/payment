package com.dbbl.payment.dto;

public class SendMoneyDto {
    private AccountTransanctionHistoryDto fromAccount;
    private AccountTransanctionHistoryDto toAccount;
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
