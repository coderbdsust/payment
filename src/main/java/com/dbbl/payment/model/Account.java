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
    private Double balance;
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


}
