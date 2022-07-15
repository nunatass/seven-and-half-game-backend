package com.sevenandhalf.domain.service.wallet;


import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.domain.entity.wallet.Transaction;
import com.sevenandhalf.domain.entity.wallet.TransactionType;
import com.sevenandhalf.domain.entity.wallet.Wallet;
import com.sevenandhalf.domain.repository.wallet.WalletRepository;
import com.sevenandhalf.domain.service.user.UserService;
import com.sevenandhalf.exception.NotFoundException;
import com.sevenandhalf.exception.User.UserNotFownedException;
import com.sevenandhalf.exception.wallet.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

  @Autowired
  private UserService userService;

  @Autowired
  private WalletRepository walletRepository;

  @Autowired
  private TransactionService transactionService;


  @Override
  public Wallet findByUserEmail(String userEmail) throws UserNotFownedException {
    User user = userService.findByEmail(userEmail);

    if (user == null) {
      throw new UserNotFownedException("User not found!");
    }

    Optional<Wallet> wallet = walletRepository.findByUserId(user.getId());

    if (wallet.isEmpty()) {
      throw new NotFoundException("Wallet not found!");
    }

    List<Transaction> transaction = transactionService.findAllByWalletId(
        wallet.get().getId());

    wallet.get().setTransactions(transaction);

    return user.getWallet();
  }

  @Override
  public Wallet findByUserId(UUID userId) {

    User user = userService.findById(userId);

    if (user == null) {
      throw new UserNotFownedException("User not found!");
    }

    Optional<Wallet> wallet = walletRepository.findByUserId(userId);

    if (wallet.isEmpty()) {
      throw new NotFoundException("Wallet not found!");
    }

    List<Transaction> transaction = transactionService.findAllByWalletId(
        wallet.get().getId());

    wallet.get().setTransactions(transaction);


    return wallet.get();
  }


  @Override
  public Wallet findById(UUID id) throws NotFoundException {
    Optional<Wallet> wallet = walletRepository.findById(id);

    if (wallet.isEmpty()) {
      throw new NotFoundException("Wallet not found!");
    }

    List<Transaction> transaction = transactionService.findAllByWalletId(id);
    wallet.get().setTransactions(transaction);
    return wallet.get();
  }

  @Override
  public List<Transaction> findAllTransactionsByWalletId(UUID walletId) throws NotFoundException {
    return transactionService.findAllByWalletId(walletId);
  }

  @Override
  public Transaction findTransactionById(UUID id) throws NotFoundException {
    return transactionService.findById(id);
  }

  @Override
  public Transaction createTransaction(UUID walletId, BigDecimal value, TransactionType type) throws NotFoundException, TransactionException {
    return transactionService.createTransaction(walletId, value, type);

  }


}
