package com.dbbl.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dbbl.payment.constants.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Road no is required")
    @Size( max = 50, message = "Road no length limit excceded")
    private String roadNo;
    @NotBlank(message = "House no is required")
    @Size( max = 50, message = "House no length limit excceded")
    private String houseNo;
    @Size(min = 2, max = 12, message = "Post code is required")
    private String postCode;
    @Size(min = 2, max = 30, message = "City is required")
    private String city;
    @Size(min = 2, max = 30, message = "Country is required")
    private String country;
    @Size(min = 2, max = 30, message = "Contact is required")
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", roadNo='" + roadNo + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", contact='" + contact + '\'' +
                ", addressType=" + addressType +
                '}';
    }
}
