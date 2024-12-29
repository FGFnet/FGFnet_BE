package com.fg.fnet.auth;

import com.fg.fnet.fg.entity.Fg;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class FgUserDetails implements UserDetails {

  private final Fg fg;

  public FgUserDetails(Fg fg) {
    this.fg = fg;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();

    authorities.add(new SimpleGrantedAuthority(fg.getRole().getKey()));

    return authorities;
  }

  @Override
  public String getPassword() {
    return fg.getPassword();
  }

  @Override
  public String getUsername() {
    return fg.getStudentId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
