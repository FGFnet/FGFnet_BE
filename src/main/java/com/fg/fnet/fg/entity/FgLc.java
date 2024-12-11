package com.fg.fnet.fg.entity;

import com.fg.fnet.common.BaseEntity;
import com.fg.fnet.lc.Lc;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class FgLc extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fg_id")
  private Fg fg;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lc_id")
  private Lc lc;

  @Builder
  public FgLc(Fg fg, Lc lc) {
    this.fg = fg;
    this.lc = lc;
  }
}
