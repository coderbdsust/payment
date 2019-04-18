package com.dbbl.payment.dto;

import com.dbbl.payment.model.Address;
import com.dbbl.payment.model.Profile;

import javax.validation.Valid;

public class ProfileViewDto {

    @Valid
    private Profile profile;
    @Valid
    private Address presentAddress;
    @Valid
    private Address parmanentAddress;
    @Valid
    private Address officeAddress;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Address getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(Address presentAddress) {
        this.presentAddress = presentAddress;
    }

    public Address getParmanentAddress() {
        return parmanentAddress;
    }

    public void setParmanentAddress(Address parmanentAddress) {
        this.parmanentAddress = parmanentAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    @Override
    public String toString() {
        return "ProfileViewDto{" +
                "profile=" + profile +
                ", presentAddress=" + presentAddress +
                ", parmanentAddress=" + parmanentAddress +
                ", officeAddress=" + officeAddress +
                '}';
    }
}
