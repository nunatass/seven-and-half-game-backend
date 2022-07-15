package com.sevenandhalf.domain.dao.auth;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.sevenandhalf.common.utils.constraints.daterange.DateRangeType;
import com.sevenandhalf.common.utils.constraints.daterange.DateRangeValid;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel("SignUpRequest")
@Data
public class SignUpRequestDto {

  @Valid
  @NotBlank(message = "username is required")
  private String username;

  @Valid
  @NotBlank(message = "password is required")
  @Size(max = 100)
  private String fullName;

  @Valid
  @NotBlank
  @Email(message = "email is required")
  @Size(max = 45)
  private String email;

  @Valid
  @NotBlank(message = "password is required")
  @Size(min = 8, max = 64)
  @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
      message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
  private String password;


  @Valid
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @DateRangeValid(target = 18, rangeType = DateRangeType.YEAR)
  private Date birthday;








}
