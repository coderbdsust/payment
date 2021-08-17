package com.dbbl.payment.service;

import com.dbbl.payment.model.Customer;
import com.dbbl.payment.service.exception.CustomerNotFoundException;

import java.util.List;

public interface ICustomerService {
    public List<Customer> findAll();
    public Customer deleteCustomer(Long customerId) throws CustomerNotFoundException;
}
