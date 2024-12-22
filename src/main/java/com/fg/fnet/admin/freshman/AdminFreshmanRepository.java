package com.fg.fnet.admin.freshman;

import com.fg.fnet.freshman.Freshman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminFreshmanRepository extends JpaRepository<Freshman, Long> {

}
