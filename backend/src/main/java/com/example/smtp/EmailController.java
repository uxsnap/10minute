package com.example.smtp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/email")
public class EmailController {
  @GetMapping
  public ArrayList<EmailDto> getEmail() {
    ReadEmail readEmail = new ReadEmail(
      Config.MAIL_HOST,
      Config.MAIL_USER,
      Config.MAIL_PASSWORD
    );

    try {
      return readEmail.read();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
}
