package com.sevenandhalf.domain.dao.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@ApiModel("UserRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdDto {

  private UUID id;

}
