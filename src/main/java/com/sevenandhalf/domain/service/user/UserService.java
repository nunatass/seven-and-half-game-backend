package com.sevenandhalf.domain.service.user;

import com.sevenandhalf.domain.dao.auth.LoginRequestDto;
import com.sevenandhalf.domain.dao.auth.LoginResponseDto;
import com.sevenandhalf.domain.dao.auth.SignUpRequestDto;
import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.exception.ConflictException;
import com.sevenandhalf.exception.UnAuthorizedException;
import com.sevenandhalf.exception.User.UserNotFownedException;

import java.util.List;
import java.util.UUID;

public interface UserService {

  User findByEmail(String email) throws UserNotFownedException;

  User findById(UUID id) throws UserNotFownedException;

  User registerUser(SignUpRequestDto signUpRequest) throws ConflictException;

  LoginResponseDto signIn(LoginRequestDto loginRequest) throws UnAuthorizedException;

  List<User> findAll();

}
