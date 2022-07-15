package com.sevenandhalf.domain.service.user;


import com.sevenandhalf.common.utils.Utils;
import com.sevenandhalf.domain.dao.auth.LoginRequestDto;
import com.sevenandhalf.domain.dao.auth.LoginResponseDto;
import com.sevenandhalf.domain.dao.auth.SignUpRequestDto;
import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.domain.entity.wallet.Wallet;
import com.sevenandhalf.domain.repository.UserRepository;
import com.sevenandhalf.exception.ConflictException;
import com.sevenandhalf.exception.UnAuthorizedException;
import com.sevenandhalf.exception.User.UserNotFownedException;
import com.sevenandhalf.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServicesImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CustomUserDetailsService userDetailsService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  @Autowired
  AuthenticationManager authenticationManager;



  @Override
  public User findByEmail(String email) {
    Optional<User> user =  userRepository.findByEmail(email);

    if(user.isPresent()) {
      throw new UserNotFownedException("User not found!");
    }

    return user.get();
  }

  @Override
  public User findById(UUID id) throws UserNotFownedException {
    Optional<User> user =  userRepository.findById(id);

    if(user.isEmpty()) {
      throw new UserNotFownedException("User not found!");
    }

    return user.get();
  }

  @Override
  public User registerUser(SignUpRequestDto signUpRequest) throws ConflictException {

   if(userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
      throw new ConflictException("Email Address already in use!");
    }

    // Creating user's account
    User user = new User();
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setFullName(signUpRequest.getFullName());
    user.setUsername(signUpRequest.getUsername());
    user.setBirthday(signUpRequest.getBirthday());

    // create wallet for user
    Wallet wallet = new Wallet();
    wallet.setUser(user);
    wallet.setBalance(BigDecimal.ZERO);
    wallet.setTransactions(new ArrayList<>());
    user.setWallet(wallet);
    userRepository.save(user);

    return user;
  }

  @Override
  public LoginResponseDto signIn(LoginRequestDto loginRequest) throws UnAuthorizedException {

    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getUsernameOrEmail(),
              loginRequest.getPassword()
          )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());

      return new LoginResponseDto(
          jwtService.generateToken(userDetails),
          jwtService.TOKEN_TYPE,
          Utils.timeConverter(jwtService.TOKEN_EXPIRATION_TIME)
      );
    }catch (Exception exception) {
      throw new UnAuthorizedException(exception.getMessage());
    }

  }

  public List<User> findAll() {
    return userRepository.findAll();
  }


}
