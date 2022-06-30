package com.sevenandhalf.domain.service.user;


import com.sevenandhalf.domain.dao.auth.LoginRequestDto;
import com.sevenandhalf.domain.dao.auth.SignUpRequestDto;
import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.domain.repository.UserRepository;
import com.sevenandhalf.exception.ConflictException;
import com.sevenandhalf.exception.UnAuthorizedException;
import com.sevenandhalf.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    return null;
  }

  @Override
  public void registerUser(SignUpRequestDto signUpRequest) throws ConflictException {

   if(userRepository.findByEmail(signUpRequest.getEmail()) != null) {
      throw new ConflictException("Email Address already in use!");
    }

    // Creating user's account
    User user = new User();
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setFullName(signUpRequest.getFullName());
    user.setUsername(signUpRequest.getUsername());
    userRepository.save(user);
  }

  @Override
  public String signIn(LoginRequestDto loginRequest) throws UnAuthorizedException {

    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getUsernameOrEmail(),
              loginRequest.getPassword()
          )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());

      return jwtService.generateToken(userDetails);

    }catch (Exception exception) {
      throw new UnAuthorizedException(exception.getMessage());
    }

  }


}
