package com.dbbl.payment.service;

import com.dbbl.payment.constants.AddressType;
import com.dbbl.payment.constants.ProfileType;
import com.dbbl.payment.dto.ProfileViewDto;
import com.dbbl.payment.model.Address;
import com.dbbl.payment.model.Profile;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.repository.AddressRepository;
import com.dbbl.payment.repository.ProfileRepository;
import com.dbbl.payment.repository.UserAccountRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public ProfileViewDto getBankProfile(Long customerId) {
        return null;
    }

    @Override
    public ProfileViewDto getUserAccountProfile(String email) {
        UserAccount userAccount = userAccountRepository.getUserAccountByEmail(email);
        Profile profile = userAccount.getProfileId();

        ProfileViewDto profileViewDto = new ProfileViewDto();
        profileViewDto.setProfile(profile);
        if (profile.getAddresses() != null) {
            for (Address address : profile.getAddresses()) {
                if (address.getAddressType() == AddressType.OFFICIAL) {
                    profileViewDto.setOfficeAddress(address);
                } else if (address.getAddressType() == AddressType.PARMANENT) {
                    profileViewDto.setParmanentAddress(address);
                } else if (address.getAddressType() == AddressType.PRESENT) {
                    profileViewDto.setPresentAddress(address);
                }
            }
        }
        if (profileViewDto.getOfficeAddress() == null) {
            profileViewDto.setOfficeAddress(new Address());
        }

        if (profileViewDto.getParmanentAddress() == null) {
            profileViewDto.setParmanentAddress(new Address());
        }
        if (profileViewDto.getPresentAddress() == null) {
            profileViewDto.setPresentAddress(new Address());
        }
        return profileViewDto;
    }

    @Override
    @Transactional
    public Profile saveOrUpdateUserAccountProfile(ProfileViewDto profileViewDto) {
        Profile profile = profileViewDto.getProfile();

        if (profile.getId() == null) {
            profile.setCreatedDate(new Date());
        }

        profile.setUpdatedDate(new Date());
        profile.setProfileType(ProfileType.USER_PROFILE);
        profile = profileRepository.save(profile);

        Address presentAddress = profileViewDto.getPresentAddress();
        presentAddress.setAddressType(AddressType.PRESENT);
        presentAddress.setProfileId(profile);
        presentAddress = addressRepository.save(presentAddress);

        Address parmanentAddress = profileViewDto.getParmanentAddress();
        parmanentAddress.setAddressType(AddressType.PARMANENT);
        parmanentAddress.setProfileId(profile);
        parmanentAddress = addressRepository.save(parmanentAddress);

        Address officialAddress = profileViewDto.getOfficeAddress();
        officialAddress.setAddressType(AddressType.OFFICIAL);
        officialAddress.setProfileId(profile);
        officialAddress = addressRepository.save(officialAddress);
        return profile;
    }
}
