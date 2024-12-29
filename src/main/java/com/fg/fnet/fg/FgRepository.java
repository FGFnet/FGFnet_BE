package com.fg.fnet.fg;

import com.fg.fnet.fg.entity.Fg;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FgRepository extends JpaRepository<Fg, Long> {

  Optional<Fg> findByName(String name);


}
