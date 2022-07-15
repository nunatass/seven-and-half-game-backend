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

  @GetMapping("/users/{userId}")
  public ResponseEntity<WalletDto> getWalletByUserId( @PathVariable UUID userId) {
    WalletDto walletDto = WalletDto.fromEntity(walletService.findByUserId(userId));
    return ResponseEntity.ok(walletDto);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<WalletDto> getWalletById( @PathVariable UUID userId) {
    WalletDto walletDto = WalletDto.fromEntity(walletService.findById(userId));
    return ResponseEntity.ok(walletDto);
  }

  @GetMapping("/{walletId}/transactions")
  public ResponseEntity<List<TransactionDto>> getWalletTransactions(@PathVariable UUID walletId) {
    List<TransactionDto> transactions  = walletService.findAllTransactionsByWalletId(walletId)
        .stream().map(TransactionDto::fromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(transactions);
  }


  @GetMapping("/transactions/{transactionId}")
  public ResponseEntity<TransactionDto> getWalletTransactionById(@PathVariable UUID transactionId) {
    TransactionDto transaction  = TransactionDto.fromEntity(walletService.findTransactionById(transactionId));
    return ResponseEntity.ok(transaction);
  }


  @PostMapping("/{walletId}/transactions")
  public ResponseEntity<TransactionDto> addTransaction(@PathVariable UUID walletId, @Valid @RequestBody
  TransactionRequestDto transaction) {

    TransactionDto createTransactionResponseDto = TransactionDto.fromEntity(walletService.createTransaction(walletId,
        transaction.getAmount(), transaction.getType()));

    return ResponseEntity.ok(createTransactionResponseDto);
  }

}

