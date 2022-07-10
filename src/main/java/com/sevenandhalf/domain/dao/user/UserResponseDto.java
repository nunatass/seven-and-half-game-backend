package com.sevenandhalf.domain.dao.user;

import com.sevenandhalf.domain.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApiModel("UserRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

  private UUID id;
  private String fullName;
  private String email;
  private String username;
  private String password;


  public static UserResponseDto fromEntity(User user) {
    UserResponseDto instance = new UserResponseDto();
    instance.setId(user.getId());
    instance.setFullName(user.getFullName());
    instance.setEmail(user.getEmail());
    instance.setUsername(user.getUsername());
    instance.setPassword(user.getPassword());
    return instance;
  }
}