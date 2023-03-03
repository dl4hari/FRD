package com.fp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    @Value("{spring.mail.user.from}")
    private String from;

    @Value("{spring.mail.user.to}")
    private String to;

    @Autowired
    EmailService emailService;

    @GetMapping(value = "/mail")
    public String sendSimpleEmail() throws Exception {

        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject("James Web");

        Map model = new HashMap();
        model.put("user", "James");


        emailService.sendEmail(mail);
        return "Email Sent..!";
    }

}
