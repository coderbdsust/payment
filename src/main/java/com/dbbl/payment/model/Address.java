package com.dbbl.payment.model;

import com.dbbl.payment.constants.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;

/*
CREATE TABLE address (
                id NUMBER NOT NULL,
                road_no VARCHAR2(50) NOT NULL,
                house_no VARCHAR2(50) NOT NULL,
                post_code VARCHAR2(50) NOT NULL,
                city VARCHAR2(50) NOT NULL,
                country VARCHAR2(50) NOT NULL,
                contact VARCHAR2(50) NOT NULL,
                address_type VARCHAR2(50) NOT NULL,
                profile_id NUMBER NOT NULL,
                CONSTRAINT ADDRESS_PK PRIMARY KEY (id)
);
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roadNo;
    private String houseNo;
    private String postCode;
    private String city;
    private String country;
    private String contact;
    private AddressType addressType;
    @ManyToOne
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    @JsonIgnore
    private Profile profileId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoadNo() {
        return roadNo;
    }

    public void setRoadNo(String roadNo) {
        this.roadNo = roadNo;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
    }
}
