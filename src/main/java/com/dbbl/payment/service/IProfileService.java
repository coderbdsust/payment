package com.dbbl.payment.service;

import com.dbbl.payment.dto.ProfileViewDto;
import com.dbbl.payment.model.Profile;

public interface IProfileService {
    public ProfileViewDto getBankProfile(Long customerId);
    public ProfileViewDto getUserAccountProfile(String email);
    public Profile saveOrUpdateUserAccountProfile(ProfileViewDto profileViewDto);
}
