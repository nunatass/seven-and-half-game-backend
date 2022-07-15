package com.sevenandhalf.domain.dao.auth;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("SignInResponse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
  private String token;
  private String token_type;
  private String expires_in;

}
