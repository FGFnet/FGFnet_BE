package com.fg.fnet.lc;

import com.fg.fnet.common.BaseEntity;
import com.fg.fnet.fg.entity.FgLc;
import com.fg.fnet.freshman.Freshman;
import com.fg.fnet.schedule.entity.Schedule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Lc extends BaseEntity {

  @Column(nullable = false, length = 6, unique = true)
  private String name;

  @ManyToOne
  @JoinColumn(name = "schedule_id")
  private Schedule schedule;

  @OneToMany(mappedBy = "lc")
  private List<FgLc> fgLcs = new ArrayList<>();

  @OneToMany(mappedBy = "lc")
  private List<Freshman> freshmans = new ArrayList<>();

  @Builder
  public Lc(String name, Schedule schedule, List<Freshman> freshmans) {
    this.name = name;
    this.schedule = schedule;
    this.freshmans = freshmans;
  }

  public LcDto toDTO() {
    return LcDto.builder()
        .lcName(name)
        .buildingDate(schedule.getDate().toString())
        .build();
  }
}
