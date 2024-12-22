package com.fg.fnet.admin.freshman;

import com.fg.fnet.freshman.FreshmanDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AdminFreshmanDto {

  @Getter
  @NoArgsConstructor
  public static class Message {

    private String freshman;

    @Builder
    public Message(String message) {
      this.freshman = message;
    }
  }

  @Getter
  @NoArgsConstructor
  public static class Freshmans {

    private List<FreshmanDto> freshmans;

    @Builder
    public Freshmans(List<FreshmanDto> freshmans) {
      this.freshmans = freshmans;
    }
  }

}
