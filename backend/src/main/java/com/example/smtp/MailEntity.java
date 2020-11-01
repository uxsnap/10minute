package com.example.smtp;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "mail")
public class MailEntity {
  @Id
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "isFree")
  private Boolean isFree;
}
