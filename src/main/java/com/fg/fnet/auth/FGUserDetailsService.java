package com.fg.fnet.auth;

import com.fg.fnet.fg.FgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FGUserDetailsService implements UserDetailsService {

  @Autowired
  private final FgRepository fgRepository;

  public FGUserDetailsService(FgRepository fgRepository) {
    this.fgRepository = fgRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return fgRepository.findByName(name)
        .map(FgUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("FG not found"));
  }
}
