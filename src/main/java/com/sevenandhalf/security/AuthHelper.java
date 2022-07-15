package com.sevenandhalf.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public class AuthHelper {

  public static UserDetails getUserDetails() {
    return (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
  }


  public static String getUserName() {
    return getUserDetails().getUsername();
  }



}

