package com.fg.fnet.admin.fg;

import com.fg.fnet.admin.fg.dto.AdminFgDto;
import com.fg.fnet.admin.fg.service.AdminFgService;
import com.fg.fnet.admin.fg.service.AdminFgServiceFacade;
import java.io.IOException;
import java.util.List;
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
  public ResponseEntity<List<Integer>> createFgs(@RequestParam("file") MultipartFile file)
      throws IOException {
    List<Integer> response = adminFgServiceFacade.uploadFgs(file);
    return ResponseEntity.ok(response);
  }
}
