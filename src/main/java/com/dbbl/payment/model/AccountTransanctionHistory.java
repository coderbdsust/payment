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
    private Double creditAmount;
    private Double debitAmount;
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
}
