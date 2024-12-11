package com.fg.fnet.admin.fg;

import com.fg.fnet.fg.entity.Fg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminFgRepository extends JpaRepository<Fg, Long> {

  Fg getByStudentId(String studentId);
}
