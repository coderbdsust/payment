package com.dbbl.payment.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/*
CREATE TABLE bank_product (
                id NUMBER NOT NULL,
                product_name VARCHAR2(50) NOT NULL,
                product_code VARCHAR2(20) NOT NULL,
                product_desc VARCHAR2(100) NOT NULL,
                product_creation_date TIMESTAMP NOT NULL,
                product_active NUMBER NOT NULL,
                CONSTRAINT BANK_PRODUCT_PK PRIMARY KEY (id)
);
 */
@Entity
public class BankProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String productCode;
    private String productDesc;
    @Temporal(value = TemporalType.DATE)
    private Date productCreationDate;
    private Boolean productActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Date getProductCreationDate() {
        return productCreationDate;
    }

    public void setProductCreationDate(Date productCreationDate) {
        this.productCreationDate = productCreationDate;
    }

    public Boolean getProductActive() {
        return productActive;
    }

    public void setProductActive(Boolean productActive) {
        this.productActive = productActive;
    }
}
