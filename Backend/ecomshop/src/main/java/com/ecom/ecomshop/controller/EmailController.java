package com.ecom.ecomshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecom.ecomshop.service.SendEmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmailController {


    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("sendEmail")
    public String sendEmail(@RequestParam String receiver,@RequestParam String subject,@RequestParam String body) {
        sendEmailService.sendEmail(receiver,subject,body);
        return "Email sent successfully";
    }
}
