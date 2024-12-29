package com.fg.fnet.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fg.fnet.fg.types.RoleType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public LoginFilter(AuthenticationManager authenticationManager,
      JwtTokenProvider jwtTokenProvider, String loginUrl) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    setFilterProcessesUrl(loginUrl);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    LoginDto loginDto = new LoginDto();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      ServletInputStream inputStream = request.getInputStream();
      String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      loginDto = objectMapper.readValue(messageBody, LoginDto.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String username = loginDto.getUsername();
    String password = loginDto.getPassword();

    //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        username, password, null);

    //token에 담은 검증을 위한 AuthenticationManager로 전달
    return authenticationManager.authenticate(authToken);
  }

  //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authentication) throws IOException {
    FgUserDetails fgUserDetails = (FgUserDetails) authentication.getPrincipal();
    String username = fgUserDetails.getUsername();
    Long expiredTime = 3 * 60 * 60 * 1000L;

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
    GrantedAuthority auth = iterator.next();

    String role = auth.getAuthority();
    if (Objects.equals(role, RoleType.REGISTER_FG.getKey())) {
      expiredTime = 8 * 60 * 60 * 1000L;
    }

    String token = jwtTokenProvider.createJwt(username, role, expiredTime);

    response.addHeader("Authorization", "Bearer " + token);
    response.getWriter().write("login success");

  }

  //로그인 실패시 실행하는 메소드
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed) throws IOException {

    response.setStatus(401);
    response.getWriter().write("login failed");
  }
}
