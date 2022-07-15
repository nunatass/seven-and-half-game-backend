package com.sevenandhalf.controller;


import com.sevenandhalf.common.Constants.BasePaths;
import com.sevenandhalf.domain.dao.user.UserDto;
import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BasePaths.USER_BASE_URL)
public class UserController {

  @Autowired
  UserService userService;


  @GetMapping("")
  public ResponseEntity<List<UserDto>> listAllUsers() {
    List<UserDto> userDtoList =  userService.findAll().stream()
        .map(UserDto::fromEntity)
        .collect(Collectors.toList());

    return ResponseEntity.ok(userDtoList);
  }


  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUser( @PathVariable UUID userId) {
    User user = userService.findById(userId);
    UserDto userDto = UserDto.fromEntity(user);

    return ResponseEntity.ok(userDto);
  }


}
