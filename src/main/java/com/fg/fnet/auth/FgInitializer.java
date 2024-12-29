package com.fg.fnet.auth;

import com.fg.fnet.fg.FgRepository;
import com.fg.fnet.fg.entity.Fg;
import com.fg.fnet.fg.types.CampusType;
import com.fg.fnet.fg.types.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FgInitializer {

  private final FgRepository fgRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public FgInitializer(FgRepository fgRepository, BCryptPasswordEncoder passwordEncoder) {
    this.fgRepository = fgRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Bean
  public CommandLineRunner initAdminAccount() {
    return args -> {
      if (fgRepository.findByName("admin").isEmpty()) {
        Fg admin = Fg.builder()
            .name("관리자")
            .studentId("admin")
            .password(passwordEncoder.encode("admin123")) // encode password bcrypt
            .role(RoleType.SUPER_ADMIN_FG)
            .campus(CampusType.NC_CAMPUS)
            .build();

        fgRepository.save(admin);
      }
    };
  }

  @Bean
  public CommandLineRunner initRegisterAccount() {
    return args -> {
      if (fgRepository.findByName("register").isEmpty()) {
        Fg register = Fg.builder()
            .name("접수1")
            .studentId("register1")
            .password(passwordEncoder.encode("register123")) // encode password bcrypt
            .role(RoleType.REGISTER_FG)
            .campus(CampusType.NC_CAMPUS)
            .build();

        fgRepository.save(register);
      }
    };
  }
}
