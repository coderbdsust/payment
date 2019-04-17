package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/*
CREATE TABLE account (
                id NUMBER NOT NULL,
                balance NUMBER NOT NULL,
                branch_id NUMBER NOT NULL,
                customer_id NUMBER NOT NULL,
                enabled RAW NOT NULL,
                account_type VARCHAR2(30) NOT NULL,
                created_by NUMBER NOT NULL,
                updated_by NUMBER NOT NULL,
                updated_date TIMESTAMP NOT NULL,
                created_date TIMESTAMP NOT NULL,
                bank_product_id_fk NUMBER NOT NULL,
                CONSTRAINT ACCOUNT_PK PRIMARY KEY (id)
);

 */

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float balance;
    @ManyToOne
    @JoinColumn(name = "branch_id",referencedColumnName = "id")
    @JsonIgnore
    private Branch branchId;
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private Customer customerId;
    private boolean enabled;
    private String accountType;
    private Long createdBy;
    @Temporal(value = TemporalType.DATE)
    private Date createdDate;
    private Long updatedBy;
    @Temporal(value = TemporalType.DATE)
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "bank_product_id",referencedColumnName = "id")
    @JsonIgnore
    private BankProduct bankProductId;
    @OneToMany(mappedBy = "accountId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AccountTransanctionHistory> accountTransanctionHistories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public BankProduct getBankProductId() {
        return bankProductId;
    }

    public void setBankProductId(BankProduct bankProductId) {
        this.bankProductId = bankProductId;
    }

    public Set<AccountTransanctionHistory> getAccountTransanctionHistories() {
        return accountTransanctionHistories;
    }

    public void setAccountTransanctionHistories(Set<AccountTransanctionHistory> accountTransanctionHistories) {
        this.accountTransanctionHistories = accountTransanctionHistories;
    }
}
