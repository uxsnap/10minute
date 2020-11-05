package com.example.smtp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TokenDto {
  private UUID token;
}
