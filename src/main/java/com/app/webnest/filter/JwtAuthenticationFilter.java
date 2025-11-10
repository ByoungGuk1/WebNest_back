package com.app.webnest.filter;

import com.app.webnest.domain.dto.UserResponseDTO;
import com.app.webnest.service.UserService;
import com.app.webnest.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// private이 붙어있는 경로는 모두 header에서 토큰을 검증한다.
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;
  private final UserService userService;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getRequestURI();
    return !path.startsWith("/private/");
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader("Authorization");
    String jwtToken = null;
    String userEmail = null;

    // 헤더에 심어져있는 userEmail을 가져온다
    if(header != null && header.startsWith("Bearer ")){
      jwtToken = header.substring(7);
      if(jwtTokenUtil.verifyJwtToken(jwtToken)){
        userEmail = (String)jwtTokenUtil.getUserEmailFromToken(jwtToken).get("userEmail");
      }
    }

    // 인증된 사용자 정보 객체를 생성하고 전달
    if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
      Long userId = userService.getUserIdByUserEmail(userEmail);
      UserResponseDTO foundUser = userService.getUserById(userId);

      if(jwtTokenUtil.verifyJwtToken(jwtToken)){
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(foundUser, null, List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }
    filterChain.doFilter(request, response);
  }
}