package com.dbbl.payment.dto;

import javax.validation.constraints.*;

public class AccountTransanctionHistoryDto {

    @Digits(integer = 3, fraction = 0, message = "Invalid branch code")
    @NotNull(message = "Invalid branch code")
    private Long branchId;
    @Digits(integer = 3, fraction = 0, message = "Invalid product code")
    @NotNull(message = "Invalid product code")
    private Long bankProductId;
    @NotNull(message = "Invalid account number")
    private Long accountId;
    private Float depositedAmount;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getBankProductId() {
        return bankProductId;
    }

    public void setBankProductId(Long bankProductId) {
        this.bankProductId = bankProductId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Float getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(Float depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    @Override
    public String toString() {
        return "AccountTransanctionHistoryDto{" +
                "branchId=" + branchId +
                ", bankProductId=" + bankProductId +
                ", accountId=" + accountId +
                ", depositedAmount=" + depositedAmount +
                '}';
    }
}
