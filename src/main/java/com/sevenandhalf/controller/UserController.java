package com.sevenandhalf.controller;


import com.sevenandhalf.common.config.Constants;
import com.sevenandhalf.domain.dao.user.UserResponseDto;
import com.sevenandhalf.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Constants.USER_BASE_URL)
public class UserController {

  @Autowired
  UserService userService;


  @GetMapping("")
  public ResponseEntity<List<UserResponseDto>> listAllUsers() {
    List<UserResponseDto> userResponseDtoList =  userService.findAll().stream()
        .map(UserResponseDto::fromEntity)
        .collect(Collectors.toList());

    return ResponseEntity.ok(userResponseDtoList);
  }


}
