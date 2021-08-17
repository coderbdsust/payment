package com.dbbl.payment.repository;

import com.dbbl.payment.model.Customer;
import com.dbbl.payment.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByProfileId(Profile profile);
}
