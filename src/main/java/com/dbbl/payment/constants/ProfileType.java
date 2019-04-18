package com.dbbl.payment.constants;

public enum ProfileType {
    BANK_PROFILE(1), USER_PROFILE(2);

    private int profileType;

    ProfileType(final int profileType){
        this.profileType=profileType;
    }

    public int getProfileType(){
        return profileType;
    }
}
