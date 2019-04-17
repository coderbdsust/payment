package com.dbbl.payment.service;

import com.dbbl.payment.dto.UserAccountDto;
import com.dbbl.payment.model.Profile;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.model.UserRole;
import com.dbbl.payment.repository.ProfileRepository;
import com.dbbl.payment.repository.UserAccountRepository;
import com.dbbl.payment.repository.UserRoleRepository;
import com.dbbl.payment.utils.RoleConversion;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<UserAccount> findAll() {
        List<UserAccount> users = userAccountRepository.findAll();
        for(UserAccount u:users){
            System.out.println(u);
        }
        return users;
    }

    @Transactional
    public UserAccount createOrUpdateSystemUser(UserAccountDto userAccountDTO) {

        if(userAccountDTO.getId()==null) {
            Profile profile = new Profile();
            profile.setFirstName(userAccountDTO.getFirstName());
            profile.setLastName((userAccountDTO.getLastName()));
            profile.setMobileNumber(userAccountDTO.getContact());
            profile.setEmail(userAccountDTO.getEmail());
            profile.setCreatedDate(new Date());
            profile.setUpdatedDate(new Date());
            profile = profileRepository.save(profile);

            UserAccount user = new UserAccount();
            user.setEmail(userAccountDTO.getEmail());
            user.setPassword(userAccountDTO.getPassword());
            user.setProfileId(profile);
            user = userAccountRepository.save(user);

            Set<UserRole> roles = RoleConversion.convertRole(userAccountDTO.getRoleName(), user);
            for (UserRole userRole : roles) {
                userRoleRepository.save(userRole);
            }
            return user;
        }
        return null;
    }

    public void deleteSystemUserById(Long id){
         userAccountRepository.deleteById(id);
    }

    @Override
    public void disableAdminUser(Long id) {
        Optional<UserAccount> result= userAccountRepository.findById(id);
        if(result.isPresent()){
            UserAccount userAccount = result.get();
            userAccount.setEnabled(!userAccount.getEnabled());
            userAccountRepository.save(userAccount);
        }
    }
}
