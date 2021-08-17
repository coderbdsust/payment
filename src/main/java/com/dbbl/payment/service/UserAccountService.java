package com.dbbl.payment.service;

import com.dbbl.payment.constants.ProfileType;
import com.dbbl.payment.dto.UserAccountDto;
import com.dbbl.payment.model.Profile;
import com.dbbl.payment.model.UserAccount;
import com.dbbl.payment.model.UserRole;
import com.dbbl.payment.repository.ProfileRepository;
import com.dbbl.payment.repository.UserAccountRepository;
import com.dbbl.payment.repository.UserRoleRepository;
import com.dbbl.payment.service.exception.SelfUserOperationException;
import com.dbbl.payment.utils.RoleConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        for (UserAccount u : users) {
            System.out.println(u);
        }
        return users;
    }

    @Transactional
    public UserAccount createSystemUser(UserAccountDto userAccountDTO) {
        Profile profile = new Profile();
        profile.setFirstName(userAccountDTO.getFirstName());
        profile.setLastName((userAccountDTO.getLastName()));
        profile.setMobileNumber(userAccountDTO.getContact());
        profile.setEmail(userAccountDTO.getEmail());
        profile.setCreatedDate(new Date());
        profile.setUpdatedDate(new Date());
        profile.setProfileType(ProfileType.USER_PROFILE);
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

    public void deleteSystemUserById(Long id, String currentUser) throws SelfUserOperationException {
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        if(userAccount.isPresent()) {
            if(userAccount.get().getEmail().equals(currentUser)){
                throw new SelfUserOperationException("Self user can't be deleted exception");
            }
        }
        userAccountRepository.deleteById(id);
    }

    @Override
    public void disableAdminUser(Long id, String loginUser) throws SelfUserOperationException, UsernameNotFoundException {
        Optional<UserAccount> result = userAccountRepository.findById(id);
        if (result.isPresent()) {
            if(result.get().getEmail().equals(loginUser)){
                throw new SelfUserOperationException("Self user disable not possible");
            }
            UserAccount userAccount = result.get();
            userAccount.setEnabled(!userAccount.getEnabled());
            userAccountRepository.save(userAccount);
            return;
        }
        throw new UsernameNotFoundException("User not found using id");
    }
}
