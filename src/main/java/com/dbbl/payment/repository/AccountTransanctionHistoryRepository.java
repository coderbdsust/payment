package com.dbbl.payment.repository;

import com.dbbl.payment.model.AccountTransanctionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountTransanctionHistoryRepository extends JpaRepository<AccountTransanctionHistory, Long> {
}

