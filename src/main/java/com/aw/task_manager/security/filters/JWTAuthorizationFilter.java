package com.aw.task_manager.security.filters;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.aw.task_manager.security.constants.SecurityConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String headerBearerToken = request.getHeader(SecurityConstants.AUTHORIZATION);

    if (headerBearerToken == null || !headerBearerToken.startsWith(SecurityConstants.BEARER)) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = headerBearerToken.replace(SecurityConstants.BEARER, "");
    String username = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
        .build()
        .verify(token)
        .getSubject();

    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Arrays.asList());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }
}
