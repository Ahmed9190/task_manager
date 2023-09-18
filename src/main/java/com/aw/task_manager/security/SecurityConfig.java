package com.aw.task_manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.aw.task_manager.security.constants.SecurityConstants;
import com.aw.task_manager.security.filters.AuthenticationFilter;
import com.aw.task_manager.security.filters.ExceptionHandlerFilter;
import com.aw.task_manager.security.filters.JWTAuthorizationFilter;
import com.aw.task_manager.security.managers.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
  private CustomAuthenticationManager customAuthenticationManager;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
    authenticationFilter.setFilterProcessesUrl("/auth/login");

    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            authorize -> authorize
                .requestMatchers(SecurityConstants.REGISTER_PATH).permitAll()
                .anyRequest().authenticated())
        .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
        .addFilter(authenticationFilter)
        .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
