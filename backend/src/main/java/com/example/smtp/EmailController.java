package com.example.smtp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {
  private final MailRepository mailRepository;

  @PostMapping
  public DomainDataDto getEmail(@RequestBody TokenDto providedToken) {
    mailRepository.updateExired();
    UUID currentToken = providedToken.getToken();

    List<MailEntity> domains = mailRepository.findAll();
    MailEntity foundDomain = domains
      .stream()
      .filter(mailEntity -> Objects.equals(mailEntity.getToken(), currentToken))
      .findFirst()
      .orElse(null);

    if (foundDomain == null)
      return DomainDataDto.empty(null);

    ReadEmail readEmail = new ReadEmail(
      foundDomain.getHost(),
      foundDomain.getName(),
      foundDomain.getPassword()
    );

    Calendar date = Calendar.getInstance();
    long t = date.getTimeInMillis();

    UUID token = UUID.randomUUID();
    if (currentToken == null) {
      foundDomain.setToken(token);
      foundDomain.setTokenExpire(new Date(t + (10 * Config.ONE_MINUTE_IN_MILLIS)));
    } else {
      foundDomain.setToken(currentToken);
    }
    try {
      mailRepository.save(foundDomain);
      return new DomainDataDto(foundDomain.getName(), foundDomain.getToken(), readEmail.read());
    } catch (Exception e) {
      return DomainDataDto.empty(token);
    }
  }
}
