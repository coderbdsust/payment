package com.dbbl.payment.repository;

import com.dbbl.payment.constants.ProfileType;
import com.dbbl.payment.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Profile findByNationalIdAndProfileType(String nationalId, ProfileType profileType);
    public Profile findByEmail(String email);
}
