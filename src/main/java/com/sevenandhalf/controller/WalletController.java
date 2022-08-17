package com.sevenandhalf.controller;


import com.sevenandhalf.common.Constants.BasePaths;
import com.sevenandhalf.domain.dao.wallet.TransactionDto;
import com.sevenandhalf.domain.dao.wallet.TransactionRequestDto;
import com.sevenandhalf.domain.dao.wallet.WalletDto;
import com.sevenandhalf.domain.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BasePaths.WALLET_BASE_URL)
public class WalletController {


  @Autowired
  WalletService walletService;

  // Retrieve a wallet by user id
  @GetMapping("/users/{userId}")
  public ResponseEntity<WalletDto> getWalletByUserId( @PathVariable UUID userId) {
    WalletDto walletDto = WalletDto.fromEntity(walletService.findByUserId(userId));
    return ResponseEntity.ok(walletDto);
  }

  // Retrieve a wallet by wallet id
  @GetMapping("/{walletId}")
  public ResponseEntity<WalletDto> getWalletById( @PathVariable UUID walletId) {
    WalletDto walletDto = WalletDto.fromEntity(walletService.findById(walletId));
    return ResponseEntity.ok(walletDto);
  }

  // Retrieve all transactions by wallet id
  @GetMapping("/{walletId}/transactions")
  public ResponseEntity<List<TransactionDto>> getWalletTransactions(@PathVariable UUID walletId) {
    List<TransactionDto> transactions  = walletService.findAllTransactionsByWalletId(walletId)
        .stream().map(TransactionDto::fromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(transactions);
  }

  // Retrieve a transaction information by transaction id
  @GetMapping("/transactions/{transactionId}")
  public ResponseEntity<TransactionDto> getWalletTransactionById(@PathVariable UUID transactionId) {
    TransactionDto transaction  = TransactionDto.fromEntity(walletService.findTransactionById(transactionId));
    return ResponseEntity.ok(transaction);
  }

  // Retrieve all transactions by wallet id
  @PostMapping("/{walletId}/transactions")
  public ResponseEntity<TransactionDto> addTransaction(@PathVariable UUID walletId, @Valid @RequestBody
  TransactionRequestDto transaction) {

    TransactionDto createTransactionResponseDto = TransactionDto.fromEntity(walletService.createTransaction(walletId,
        transaction.getAmount(), transaction.getType()));

    return ResponseEntity.ok(createTransactionResponseDto);
  }

}

