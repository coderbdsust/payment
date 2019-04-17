package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;

/*
CREATE TABLE beneficiar_account (
                id NUMBER NOT NULL,
                receiver_name VARCHAR2(100) NOT NULL,
                receiver_account VARCHAR2(20) NOT NULL,
                receiver_bank_name VARCHAR2(120) NOT NULL,
                receiver_branch_name VARCHAR2(60) NOT NULL,
                receiver_branch_district_name VARCHAR2(100) NOT NULL,
                receiver_bank_routing VARCHAR2(30) NOT NULL,
                receiver_alias_name VARCHAR2(100) NOT NULL,
                receiver_is_on_same_bank NUMBER NOT NULL,
                customer_id_fk NUMBER NOT NULL,
                CONSTRAINT BENEFICIAR_ACCOUNT_PK PRIMARY KEY (id)
);
 */

@Entity
public class BeneficiarAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String receiverName;
    private String receiverAccount;
    private String receiverBankName;
    private String receiverBranchName;
    private String receiverBranchDistrictName;
    private String receiverBankRouting;
    private String receiverAliasName;
    private boolean receiverIsOnSameBank;
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private Customer customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getReceiverBankName() {
        return receiverBankName;
    }

    public void setReceiverBankName(String receiverBankName) {
        this.receiverBankName = receiverBankName;
    }

    public String getReceiverBranchName() {
        return receiverBranchName;
    }

    public void setReceiverBranchName(String receiverBranchName) {
        this.receiverBranchName = receiverBranchName;
    }

    public String getReceiverBranchDistrictName() {
        return receiverBranchDistrictName;
    }

    public void setReceiverBranchDistrictName(String receiverBranchDistrictName) {
        this.receiverBranchDistrictName = receiverBranchDistrictName;
    }

    public String getReceiverBankRouting() {
        return receiverBankRouting;
    }

    public void setReceiverBankRouting(String receiverBankRouting) {
        this.receiverBankRouting = receiverBankRouting;
    }

    public String getReceiverAliasName() {
        return receiverAliasName;
    }

    public void setReceiverAliasName(String receiverAliasName) {
        this.receiverAliasName = receiverAliasName;
    }

    public boolean isReceiverIsOnSameBank() {
        return receiverIsOnSameBank;
    }

    public void setReceiverIsOnSameBank(boolean receiverIsOnSameBank) {
        this.receiverIsOnSameBank = receiverIsOnSameBank;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }
}
