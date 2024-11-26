package com.fg.fnet.admin.lc;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${api.prefix}/admin/lc")
public class AdminLcController {

  private final AdminLcService adminLcService;

  public AdminLcController(AdminLcService adminLcService) {
    this.adminLcService = adminLcService;
  }

  @GetMapping
  public ResponseEntity<AdminLcDto> getLcs() {
    return ResponseEntity.ok(adminLcService.getLcs());
  }

  @PostMapping
  public ResponseEntity<AdminLcDto> createLcs(@RequestParam("file") MultipartFile file)
      throws IOException {
    AdminLcDto response = adminLcService.uploadLcs(file);
    return ResponseEntity.ok(response);
  }
}
