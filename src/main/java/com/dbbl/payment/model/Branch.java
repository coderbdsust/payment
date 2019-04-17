package com.dbbl.payment.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/*
CREATE TABLE branch (
                id NUMBER NOT NULL,
                branch_name VARCHAR2(50) NOT NULL,
                branch_address VARCHAR2(120) NOT NULL,
                branch_code VARCHAR2(50) NOT NULL,
                branch_contact VARCHAR2(50) NOT NULL,
                branch_routing_number VARCHAR2(30) NOT NULL,
                CONSTRAINT BRANCH_PK PRIMARY KEY (id)
);
 */

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String branchName;
    private String branchAddress;
    private String branchCode;
    private String branchContact;
    private String branchRoutingNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchContact() {
        return branchContact;
    }

    public void setBranchContact(String branchContact) {
        this.branchContact = branchContact;
    }

    public String getBranchRoutingNumber() {
        return branchRoutingNumber;
    }

    public void setBranchRoutingNumber(String branchRoutingNumber) {
        this.branchRoutingNumber = branchRoutingNumber;
    }
}
