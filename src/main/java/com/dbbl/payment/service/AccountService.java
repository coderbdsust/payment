package com.dbbl.payment.service;

import com.dbbl.payment.constants.AddressType;
import com.dbbl.payment.constants.ProfileType;
import com.dbbl.payment.dto.AccountTransanctionHistoryDto;
import com.dbbl.payment.dto.CustomerDto;
import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.Address;
import com.dbbl.payment.model.Customer;
import com.dbbl.payment.model.Profile;
import com.dbbl.payment.repository.AccountRepository;
import com.dbbl.payment.repository.AddressRepository;
import com.dbbl.payment.repository.CustomerRepository;
import com.dbbl.payment.repository.ProfileRepository;
import com.dbbl.payment.service.exception.AccountNumberNotFoundException;
import com.dbbl.payment.service.exception.OperationOnDeletedCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public Customer createAccount(CustomerDto customerDto) {

        Profile previousProfile = profileRepository
                .findByNationalIdAndProfileType(customerDto.getProfile().getNationalId(), ProfileType.BANK_PROFILE);
        if (previousProfile == null) {
            Address presentAddress = customerDto.getPresentAddress();
            presentAddress.setAddressType(AddressType.PRESENT);
            Address parmanentAddress = customerDto.getParmanentAddress();
            parmanentAddress.setAddressType(AddressType.PARMANENT);
            
//            Address officeAddress = customerDto.getOfficeAddress();
//            officeAddress.setAddressType(AddressType.OFFICIAL);
            
            addressRepository.save(presentAddress);
            addressRepository.save(parmanentAddress);
//            addressRepository.save(officeAddress);
            Profile profile = customerDto.getProfile();
            profile.setCreatedDate(new Date());
            profile.setUpdatedDate(new Date());
            profile.setProfileType(ProfileType.BANK_PROFILE);
            profileRepository.save(profile);
            Customer customer = new Customer();
            customer.setActive(true);
            customer.setProfileId(profile);
            customer.setBranchId(customerDto.getAccount().getBranchId());
            customer = customerRepository.save(customer);
            Account account = customerDto.getAccount();
            createAccount(account, customer);
            return customer;
        } else {
            Customer customer = customerRepository.findByProfileId(previousProfile);
            Account account = customerDto.getAccount();
            createAccount(account, customer);
            return customer;
        }
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    private Account createAccount(Account account, Customer customer) {
        account.setCustomerId(customer);
        account.setEnabled(true);
        return accountRepository.save(account);
    }

    @Override
    public Account findAccountInformation(AccountTransanctionHistoryDto dto) throws AccountNumberNotFoundException {
        Optional<Account> account = accountRepository.findById(dto.getAccountId());
        if (account.isPresent()) {
            return account.get();
        }

        throw new AccountNumberNotFoundException("Account number doesn't exist");
    }

    @Override
    public Account deactivateOrActivateAccount(Long id) throws AccountNumberNotFoundException, OperationOnDeletedCustomerException {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent()) {
            if(optionalAccount.get().getCustomerId().getDeleted()){
                throw new OperationOnDeletedCustomerException("Operation not possible for deleted customer");
            }
            Account account = optionalAccount.get();
            account.setEnabled(!account.isEnabled());
            return accountRepository.save(account);
        }
        throw new AccountNumberNotFoundException("Account doesn't exist");
    }
}
