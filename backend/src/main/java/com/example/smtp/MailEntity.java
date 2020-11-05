package com.example.smtp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Table(schema = "10minute", name = "mail")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MailEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "host")
  private String host;

  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String name;

  @Column(name = "token")
  @Type(type = "uuid-char")
  private UUID token;

  @Column(name = "token_expire")
  private Date tokenExpire;
}
