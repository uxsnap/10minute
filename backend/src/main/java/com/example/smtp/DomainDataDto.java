package com.example.smtp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DomainDataDto {
  private String domain;
  private UUID token;
  private ArrayList<EmailDto> emails;

  public static DomainDataDto empty(UUID token) {
    return new DomainDataDto(null, token, new ArrayList<>());
  }
}
