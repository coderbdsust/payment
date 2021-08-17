package com.dbbl.payment.repository;

import com.dbbl.payment.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByIdAndEnabled(Long id, boolean enabled);
}
