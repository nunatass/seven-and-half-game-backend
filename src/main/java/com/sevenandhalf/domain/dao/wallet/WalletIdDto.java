package com.sevenandhalf.domain.dao.wallet;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApiModel("WalletRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletIdDto {

  private UUID id;

}
