package com.dbbl.payment.service;

import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.Customer;
import com.dbbl.payment.repository.AccountRepository;
import com.dbbl.payment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService implements  ICustomerService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer deleteCustomer(Long customerId) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            Set<Account> accounts = customer.getAccounts();
            for(Account account:accounts){
                account.setEnabled(false);
                accountRepository.save(account);

            }
            customer.setDeleted(true);
            return customerRepository.save(customer);
        }

        throw new CustomerNotFoundException("Customer not found using id");
    }
}
