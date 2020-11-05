package com.example.smtp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MailRepository extends JpaRepository<MailEntity, Long> {
  @Transactional
  @Modifying
  @Query("UPDATE MailEntity " +
    "SET token  = '', " +
    "tokenExpire = '' " +
    "where tokenExpire < current_date")
  void updateExired();
}
