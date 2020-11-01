package com.example.smtp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MailRepository extends JpaRepository<MailEntity, Long> {
  ArrayList<MailEntity> findAllByIsFree();
}
