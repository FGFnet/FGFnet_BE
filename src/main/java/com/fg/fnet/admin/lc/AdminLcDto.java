package com.fg.fnet.admin.lc;

import com.fg.fnet.lc.LcDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminLcDto {

  private List<LcDto> lcs;

  @Builder
  public AdminLcDto(List<LcDto> lcs) {
    this.lcs = lcs;
  }
}
