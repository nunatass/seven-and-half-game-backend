package com.sevenandhalf.controller;


import com.sevenandhalf.common.Constants.BasePaths;
import com.sevenandhalf.domain.dao.auth.LoginRequestDto;
import com.sevenandhalf.domain.dao.auth.LoginResponseDto;
import com.sevenandhalf.domain.dao.auth.SignUpRequestDto;
import com.sevenandhalf.domain.dao.user.UserDto;
import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.domain.service.user.UserService;
import com.sevenandhalf.exception.ConflictException;
import com.sevenandhalf.exception.UnAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(BasePaths.AUTH_BASE_URL)
public class AuthController {


  @Autowired
  UserService userService;

  // Sign up a user
  @PostMapping("/signup")
  public ResponseEntity<UserDto> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequest) throws ConflictException {
    User user = userService.registerUser(signUpRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.fromEntity(user));
  }

  // Login a user
  @CrossOrigin
  @PostMapping("/signin")
  public ResponseEntity<LoginResponseDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) throws UnAuthorizedException {
    return ResponseEntity.ok(userService.signIn(loginRequest));

  }

}
