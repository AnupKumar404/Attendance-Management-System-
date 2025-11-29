package com.attendanceApp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String username){

        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(username);
            mail.setSubject("Welcome to attendance management system!");
            mail.setText("Hey, "+username+" welcome to this platform where management became ease at your fingertips...");
            javaMailSender.send(mail);
            log.info("Email sent successfully.....");
        }catch (Exception e){
            log.error("error_"+e.getMessage());
        }
    }
}