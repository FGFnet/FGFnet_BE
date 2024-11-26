package com.fg.fnet.admin.schedule;


import com.fg.fnet.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminScheduleRepository extends JpaRepository<Schedule, Long> {

  Schedule getReferenceByDay(Integer day);

}
