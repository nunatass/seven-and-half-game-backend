package com.sevenandhalf.domain.service.user;

import com.sevenandhalf.domain.dao.auth.LoginRequestDto;
import com.sevenandhalf.domain.dao.auth.LoginResponseDto;
import com.sevenandhalf.domain.dao.auth.SignUpRequestDto;
import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.exception.ConflictException;
import com.sevenandhalf.exception.UnAuthorizedException;

import java.util.List;

public interface UserService {

  User findByEmail(String email);

  void registerUser(SignUpRequestDto signUpRequest) throws ConflictException;

  LoginResponseDto signIn(LoginRequestDto loginRequest) throws UnAuthorizedException;

  List<User> findAll();

}
