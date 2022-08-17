package com.sevenandhalf.domain.service.user;


import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(email);

    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Email Not found " + email);
    }

    return new org.springframework.security.core.userdetails.User(
        user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
  }

  public UserDetails getAuthenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return loadUserByUsername(authentication.getName());
  }
}
