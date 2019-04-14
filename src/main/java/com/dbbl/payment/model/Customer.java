package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/*
CREATE TABLE customer (
                id NUMBER NOT NULL,
                active RAW NOT NULL,
                branch_id NUMBER NOT NULL,
                locked NUMBER NOT NULL,
                created_by NUMBER NOT NULL,
                created_date TIMESTAMP NOT NULL,
                updated_by NUMBER NOT NULL,
                updated_date TIMESTAMP NOT NULL,
                profile_id_fk NUMBER NOT NULL,
                CONSTRAINT CUSTOMER_PK PRIMARY KEY (id)
);
 */

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "branch_id",referencedColumnName = "id")
    @JsonIgnore
    private Branch branchId;
    private Boolean locked;
    private Long createdBy;
    @Temporal(value = TemporalType.DATE)
    private Date createdDate;
    private Long updatedBy;
    @Temporal(value = TemporalType.DATE)
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    @JsonIgnore
    private Profile profileId;
    @OneToMany(mappedBy = "customerId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BeneficiarAccount> beneficiarAccounts;
}