package com.training.library;

import com.training.library.model.Mail;
import com.training.library.model.Product;
import com.training.library.service.impl.EmailSenderServiceImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTests {

  @Autowired
  EmailSenderServiceImpl senderService;

  @Test
  void contextLoads() throws MessagingException {
    System.out.println("sending sample email");

    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put("name", "Yuliya!");
    properties.put("totalPrice", 5.18);
    properties.put("list", List.of(new Product("title", 5.18)));

    Mail mail = Mail.builder()
            .from("testmailappwise@gmail.com")
            .to("yuliya.aheeva@gmail.com")
            .htmlTemplate(new Mail.HtmlTemplate("sample", properties))
            .subject("This is sample email with spring boot and thymeleaf")
            .build();

    senderService.sendEmail(mail, null);
    System.out.println("sample email sent");
  }

}
