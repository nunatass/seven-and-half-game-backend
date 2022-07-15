package com.sevenandhalf.domain.service.wallet;

import com.sevenandhalf.domain.entity.wallet.Transaction;
import com.sevenandhalf.domain.entity.wallet.TransactionType;
import com.sevenandhalf.exception.NotFoundException;
import com.sevenandhalf.exception.wallet.TransactionException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionService {

  List<Transaction> findAllByWalletId(UUID walletId) throws NotFoundException;
  Transaction createTransaction(UUID walletId, BigDecimal value, TransactionType type)
      throws NotFoundException, TransactionException;

  Transaction findById(UUID id) throws NotFoundException;
}
