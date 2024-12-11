package com.fg.fnet.admin.fg.dto;

import com.fg.fnet.fg.dto.FgDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminFgDto {

  private List<FgDto> fgs;

  @Builder
  public AdminFgDto(List<FgDto> fgs) {
    this.fgs = fgs;
  }
}
