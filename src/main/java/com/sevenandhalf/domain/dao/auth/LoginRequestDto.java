package com.sevenandhalf.domain.dao.auth;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@ApiModel("SignInRequest")
@Data
public class LoginRequestDto {

  @Valid
  @NotBlank(message = "username or email is required")
  private String usernameOrEmail;

  @Valid
  @NotBlank(message = "password is required")
  private String password;
}
