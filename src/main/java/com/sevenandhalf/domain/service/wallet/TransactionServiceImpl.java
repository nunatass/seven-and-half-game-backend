package com.sevenandhalf.domain.service.wallet;


import com.sevenandhalf.domain.entity.wallet.Transaction;
import com.sevenandhalf.domain.entity.wallet.TransactionType;
import com.sevenandhalf.domain.entity.wallet.Wallet;
import com.sevenandhalf.domain.repository.wallet.TransactionRepository;
import com.sevenandhalf.domain.repository.wallet.WalletRepository;
import com.sevenandhalf.exception.NotFoundException;
import com.sevenandhalf.exception.UnAuthorizedException;
import com.sevenandhalf.exception.User.UserNotFownedException;
import com.sevenandhalf.exception.wallet.TransactionException;
import com.sevenandhalf.security.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private WalletRepository walletRepository;

  @Override
  public List<Transaction> findAllByWalletId(UUID walletId) throws NotFoundException {

    Optional<Wallet> wallet = walletRepository.findById(walletId);

    if (wallet.isEmpty()) {
      throw new NotFoundException("Wallet not found!");
    }

    Optional<List<Transaction>> transactions = transactionRepository.findAllTransactionsByWalletId(walletId);

    return transactions.get();
  }


/*

  String userEmail = userDetails.getUsername();*/




  @Override
  public Transaction createTransaction(UUID walletId, BigDecimal amount, TransactionType type) throws NotFoundException,
      TransactionException, UnAuthorizedException {

    if(amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new TransactionException("Transaction amount must be greater than 0!");
    }

    Optional<Wallet> wallet = walletRepository.findById(walletId);

    if(wallet.isEmpty()) {
      throw new NotFoundException("Wallet not found!");
    }

    Wallet walletToUpdate = wallet.get();
    Transaction transaction = new Transaction();

    if(type == TransactionType.DEPOSIT) {

      if (amount.compareTo(new BigDecimal("5.0")) < 0) {
        throw new TransactionException("Transaction amount must be greater or equal then 5!");
      }

      walletToUpdate.setBalance(walletToUpdate.getBalance().add(amount));
      transaction.setBalanceAfterTransaction(walletToUpdate.getBalance());
    } else {


      boolean iswalletbelongUser = walletRepository.existsByIdAndUserEmail(walletId,
          AuthHelper.getUserName());

      if(!iswalletbelongUser) {
        throw new UnAuthorizedException("Can not withdraw from this wallet!");
      }

      if(walletToUpdate.getBalance().compareTo(amount) < 0) {
        throw new TransactionException("Not enough money in wallet!");
      }

      walletToUpdate.setBalance(walletToUpdate.getBalance().subtract(amount));
      transaction.setBalanceAfterTransaction(walletToUpdate.getBalance());
    }


    transaction.setWallet(wallet.get());
    transaction.setAmount(amount);
    transaction.setType(type);
    transaction.setCreatedAt(new Date());
    walletRepository.save(walletToUpdate);
    return transactionRepository.save(transaction);

  }

  @Override
  public Transaction findById(UUID id) throws NotFoundException {
    return transactionRepository.findById(id).orElseThrow(() ->
        new NotFoundException("Transaction not found!"));
  }

}
