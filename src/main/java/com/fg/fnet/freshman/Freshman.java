package com.fg.fnet.freshman;

import com.fg.fnet.common.BaseEntity;
import com.fg.fnet.lc.Lc;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor
@Entity
public class Freshman extends BaseEntity {

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false, length = 12, unique = true)
  private String studentId;

  @Column(nullable = false, length = 17)
  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DepartType department;

  @Column(nullable = false)
  @ColumnDefault("false")
  private Boolean registered = false;

  @ManyToOne
  @JoinColumn(name = "lc_id")
  private Lc lc;

  @Builder
  public Freshman(String name, String studentId, String phoneNumber, DepartType department,
      Boolean registered, Lc lc) {
    this.name = name;
    this.studentId = studentId;
    this.phoneNumber = phoneNumber;
    this.department = department;
    this.registered = registered;
    this.lc = lc;
  }

  public FreshmanDto toDTO() {
    return FreshmanDto.builder()
        .name(name)
        .studentId(studentId)
        .phoneNum(phoneNumber)
        .department(department.getDescription())
        .lc(lc.getName())
        .build();
  }

}