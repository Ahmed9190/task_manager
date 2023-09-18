package com.aw.task_manager.security.filters;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.aw.task_manager.exceptions.EntityNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (EntityNotFoundException e) {
      handleResponse(response, "Username doesn't exist", HttpServletResponse.SC_NOT_FOUND);
    } catch (JWTVerificationException e) {
      handleResponse(response, "JWT is not valid", HttpServletResponse.SC_FORBIDDEN);
    } catch (RuntimeException e) {
      handleResponse(response, "BAD REQUEST", HttpServletResponse.SC_BAD_REQUEST);
    }
  }

  private void handleResponse(HttpServletResponse response, String message, int statusCode) throws IOException {
    response.setStatus(statusCode);
    response.getWriter().write(message);
    response.getWriter().flush();
  }
}
