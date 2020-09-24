package com.github.mailsend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Slf4j
@RestController
public class MailSenderController {

    private final JavaMailSender mailSender;

    public MailSenderController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/send")
    public ResponseEntity send() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject("test");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("skyghy3342@gmail.com"));
        message.setFrom("skyghy3342@gmail.com");
        message.setText("hi");
        message.setSentDate(new Date());
        //mailSender.send(message);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("skyghy3342@gmail.com");
        msg.setFrom("skyghy3342@gmail.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        mailSender.send(msg);
        return ResponseEntity.ok("OK");
    }

}
