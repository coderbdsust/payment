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
}
