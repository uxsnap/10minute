package com.example.smtp;

import lombok.AllArgsConstructor;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;

@AllArgsConstructor
class ReadEmail {
  private final String host;
  private final String user;
  private final String password;

  public ArrayList<EmailDto> read() throws Exception {
    // connect to my pop3 inbox
    Properties properties = System.getProperties();
    Session session = Session.getDefaultInstance(properties);
    Store store = session.getStore("pop3");
    store.connect(
      this.host,
      this.user,
      this.password
    );
    Folder inbox = store.getFolder("Inbox");
    inbox.open(Folder.READ_ONLY);

    Message[] messages = inbox.getMessages();


    ArrayList<EmailDto> emailDtos = new ArrayList<>();

    if (messages.length == 0)
      return emailDtos;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    for (Message message: messages) {
      EmailDto emailDto = new EmailDto();
      emailDto.setFrom(Arrays.toString(message.getFrom()));
      emailDto.setSubject(message.getSubject());
      Date sentDate = message.getSentDate();
      if (sentDate != null)
        emailDto.setDate(simpleDateFormat.format(message.getSentDate()));
      emailDto.setContentType(message.isMimeType("text/plain") ? "text" : "multipart");
      emailDto.setContentText(message.getContent().toString());
      emailDtos.add(emailDto);
    }

    inbox.close(true);
    store.close();

    return emailDtos;
  }

}