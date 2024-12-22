package com.fg.fnet.admin.freshman;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${api.prefix}/admin/freshman")
public class AdminFreshmanController {

  private final AdminFreshmanService adminFreshmanService;

  public AdminFreshmanController(AdminFreshmanService adminFreshmanService) {
    this.adminFreshmanService = adminFreshmanService;
  }

  @GetMapping
  public ResponseEntity<AdminFreshmanDto.Freshmans> getAllFreshman() {
    return ResponseEntity.ok(adminFreshmanService.getFreshmans());
  }

  @PostMapping
  public ResponseEntity<AdminFreshmanDto.Message> createFreshman(MultipartFile file)
      throws IOException {
    return ResponseEntity.ok(adminFreshmanService.uploadFreshmans(file));
  }

}
