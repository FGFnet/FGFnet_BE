package com.fg.fnet.admin.fg;

import com.fg.fnet.admin.fg.dto.AdminFgDto;
import com.fg.fnet.admin.fg.dto.AdminFgFacadeDto;
import com.fg.fnet.admin.fg.service.AdminFgService;
import com.fg.fnet.admin.fg.service.AdminFgServiceFacade;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${api.prefix}/admin/fg")
public class AdminFgController {

  private final AdminFgService adminFgService;
  private final AdminFgServiceFacade adminFgServiceFacade;

  public AdminFgController(AdminFgService adminFgService,
      AdminFgServiceFacade adminFgServiceFacade) {
    this.adminFgService = adminFgService;
    this.adminFgServiceFacade = adminFgServiceFacade;
  }

  @GetMapping
  public ResponseEntity<AdminFgDto> getAllFg() {
    return ResponseEntity.ok(adminFgService.getFgs());
  }

  @PostMapping
  public ResponseEntity<AdminFgFacadeDto> createFgs(@RequestParam("file") MultipartFile file)
      throws IOException {
    AdminFgFacadeDto response = adminFgServiceFacade.uploadFgs(file);
    if (response.getCode() == 200) {
      return ResponseEntity.ok(response);
    } else {
      // response에서 code 필드를 제거한 후 반환
      return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(response);
    }
  }
}
