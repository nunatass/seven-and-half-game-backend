package com.sevenandhalf.domain.repository.wallet;


import com.sevenandhalf.domain.entity.wallet.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
  Optional<List<Transaction>> findAllTransactionsByWalletId(UUID walletId);

}

