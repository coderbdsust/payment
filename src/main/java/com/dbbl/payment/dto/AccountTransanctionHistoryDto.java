package com.dbbl.payment.dto;

public class AccountTransanctionHistoryDto {
    private Long branchId;
    private Long bankProductId;
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
