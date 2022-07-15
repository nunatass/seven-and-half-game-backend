package com.sevenandhalf.domain.dao.wallet;

import com.sevenandhalf.domain.entity.wallet.TransactionType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@ApiModel("Transaction Request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {
  private BigDecimal amount;
  private TransactionType type;

}
