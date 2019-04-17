package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/*
CREATE TABLE account_transanction_history (
                id NUMBER NOT NULL,
                credit_amount NUMBER NOT NULL,
                transanction_date TIMESTAMP NOT NULL,
                transanction_type VARCHAR2(30) NOT NULL,
                from_account VARCHAR2 NOT NULL,
                to_account VARCHAR2 NOT NULL,
                reference VARCHAR2(200) NOT NULL,
                debit_amount NUMBER NOT NULL,
                account_id NUMBER NOT NULL,
                CONSTRAINT ACCOUNT_TRANSANCTION_HISTOR881 PRIMARY KEY (id)
);
 */
@Entity
public class AccountTransanctionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float creditAmount;
    private Float debitAmount;
    @Temporal(value = TemporalType.DATE)
    private Date transanctionDate;
    private String transanctionType;
    private String fromAccount;
    private String toAccount;
    private String reference;
    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    @JsonIgnore
    private Account accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Float creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Float getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Float debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Date getTransanctionDate() {
        return transanctionDate;
    }

    public void setTransanctionDate(Date transanctionDate) {
        this.transanctionDate = transanctionDate;
    }

    public String getTransanctionType() {
        return transanctionType;
    }

    public void setTransanctionType(String transanctionType) {
        this.transanctionType = transanctionType;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }
}