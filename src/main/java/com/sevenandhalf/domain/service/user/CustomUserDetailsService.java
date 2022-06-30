package com.sevenandhalf.domain.service.user;


import com.sevenandhalf.domain.entity.User;
import com.sevenandhalf.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("email Not found" + email);
    }

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(), new ArrayList<>());
  }
}
