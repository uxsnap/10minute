package com.example.smtp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDto {
  private String date;
  private String from;
  private String subject;
  private String contentText;
  private String contentType;
}
