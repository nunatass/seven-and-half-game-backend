package com.sevenandhalf.domain.service.wallet;

import com.sevenandhalf.domain.entity.wallet.Transaction;
import com.sevenandhalf.domain.entity.wallet.TransactionType;
import com.sevenandhalf.domain.entity.wallet.Wallet;
import com.sevenandhalf.exception.NotFoundException;
import com.sevenandhalf.exception.User.UserNotFownedException;
import com.sevenandhalf.exception.wallet.TransactionException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface WalletService {
  Wallet findByUserEmail(String userEmail) throws UserNotFownedException;

  Wallet findByUserId(UUID userId) throws UserNotFownedException;

  Wallet findById(UUID id) throws NotFoundException;

  List<Transaction> findAllTransactionsByWalletId(UUID walletId) throws NotFoundException;


  Transaction findTransactionById(UUID id) throws NotFoundException;

  Transaction createTransaction(UUID walletId, BigDecimal value, TransactionType type) throws NotFoundException, TransactionException;;

}
