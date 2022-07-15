package com.sevenandhalf.domain.dao.wallet;

import com.sevenandhalf.domain.entity.wallet.Transaction;
import com.sevenandhalf.domain.entity.wallet.TransactionType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@ApiModel("Create Transaction Response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
  private UUID id;
  private UUID walletId;
  private TransactionType type;
  private BigDecimal transactionAmount;
  private BigDecimal balanceAfterTransaction;
  private Date createdAt;


  public static TransactionDto fromEntity(Transaction transaction) {
    TransactionDto instance = new TransactionDto();
    instance.setId(transaction.getId());
    instance.setTransactionAmount(transaction.getAmount());
    instance.setType(transaction.getType());
    instance.setWalletId(transaction.getWallet().getId());
    instance.setBalanceAfterTransaction(transaction.getBalanceAfterTransaction());
    instance.setCreatedAt(transaction.getCreatedAt());
    return instance;
  }

}
