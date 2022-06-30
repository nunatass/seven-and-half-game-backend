package com.sevenandhalf.controller;


import com.sevenandhalf.common.config.Constants;
import com.sevenandhalf.domain.dao.auth.LoginRequestDto;
import com.sevenandhalf.domain.dao.auth.LoginResponseDto;
import com.sevenandhalf.domain.dao.auth.SignUpRequestDto;
import com.sevenandhalf.domain.service.user.UserService;
import com.sevenandhalf.exception.ConflictException;
import com.sevenandhalf.exception.UnAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.AUTH_BASE_URL)
public class AuthController {


  @Autowired
  UserService userService;


  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequest) throws ConflictException {
    userService.registerUser(signUpRequest);
    return ResponseEntity.ok().build();
  }


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) throws UnAuthorizedException {
    String jwt = userService.signIn(loginRequest);
    return ResponseEntity.ok(new LoginResponseDto(jwt));

  }


}
