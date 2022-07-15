package com.sevenandhalf.domain.dao.wallet;

import com.sevenandhalf.domain.dao.user.UserDto;
import com.sevenandhalf.domain.entity.wallet.Wallet;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApiModel("Wallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {

  private UUID id;
  private BigDecimal balance;
  private UserDto user;
  private List<TransactionDto> transactions;


  public static WalletDto fromEntity(Wallet wallet) {
    WalletDto instance = new WalletDto();
    instance.setId(wallet.getId());
    instance.setBalance(wallet.getBalance());
    instance.setUser(UserDto.fromEntity(wallet.getUser()));
    instance.getUser().setWallet(null);

    if(wallet.getTransactions() != null) {
      instance.setTransactions(wallet.getTransactions().stream()
          .map(TransactionDto::fromEntity)
          .collect(Collectors.toList()));
    }
    else {
      instance.setTransactions(new ArrayList<>());
    }

    return instance;
  }

}
