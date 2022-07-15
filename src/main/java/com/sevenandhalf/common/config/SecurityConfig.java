package com.sevenandhalf.common.config;


import com.sevenandhalf.common.Constants.BasePaths;
import com.sevenandhalf.domain.service.user.CustomUserDetailsService;
import com.sevenandhalf.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final String SIGN_UP_URL = BasePaths.AUTH_BASE_URL + "/signup";
  private final String LOGIN_URL = BasePaths.AUTH_BASE_URL + "/signin";

  @Autowired
  CustomUserDetailsService customUserDetailsService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
    auth.userDetailsService(customUserDetailsService);
  }


  @Override
  public void configure(HttpSecurity security) throws Exception {
    security.csrf().disable()
        .authorizeRequests()
        .antMatchers( LOGIN_URL, SIGN_UP_URL).permitAll()
        .anyRequest().authenticated()
        .and().cors()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    security.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }



  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
  }



}