package com.fg.fnet.fg.entity;

import com.fg.fnet.common.BaseEntity;
import com.fg.fnet.fg.dto.FgDto;
import com.fg.fnet.fg.types.CampusType;
import com.fg.fnet.fg.types.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Fg extends BaseEntity {

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false, length = 12, unique = true)
  private String studentId;

//  @Enumerated(EnumType.STRING)
//  @Column(nullable = false)
//  private FgType type;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RoleType role;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CampusType campus;

  @Column(length = 100)
  private String password;

  @OneToMany(mappedBy = "fg")
  private List<FgLc> fgLcs = new ArrayList<>();

  @Builder
  public Fg(String name, String studentId, RoleType role, CampusType campus,
      String password) {
    this.name = name;
    this.studentId = studentId;
    this.role = role;
    this.campus = campus;
    this.password = password;
  }

  public FgDto toDTO() {
    return FgDto.builder()
        .name(name)
        .studentId(studentId)
        .role(role.getKey())
        .campus(campus.getDescription())
        .lcs(fgLcs.stream().map(fgLc -> fgLc.getLc().getName()).toList())
        .build();
  }
}

