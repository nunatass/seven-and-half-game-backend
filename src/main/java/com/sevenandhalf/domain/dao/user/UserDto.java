package com.sevenandhalf.domain.dao.user;

import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.domain.entity.wallet.Wallet;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.UUID;

@ApiModel("User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private UUID id;
  private String fullName;
  private String email;
  private String username;
  private String password;
  private Date birthday;
  private Wallet wallet;


  public static UserDto fromEntity(User user) {
    UserDto instance = new UserDto();
    instance.setId(user.getId());
    instance.setFullName(user.getFullName());
    instance.setEmail(user.getEmail());
    instance.setUsername(user.getUsername());
    instance.setPassword(user.getPassword());
    instance.setBirthday(user.getBirthday());
    instance.setWallet(user.getWallet());
    return instance;
  }
}