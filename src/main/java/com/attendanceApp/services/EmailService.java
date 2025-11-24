package com.attendanceApp.services;

import com.attendanceApp.dtos.EmailSendDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public CompletableFuture<String> sendEmail(EmailSendDto dto){

        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(dto.to);
            mail.setSubject(dto.subject);
            mail.setText(dto.body);
            javaMailSender.send(mail);
            log.info("send email successfully");
            return CompletableFuture.completedFuture("Sent Email");
        }catch (Exception e){
            return CompletableFuture.completedFuture("error "+e.getMessage());
        }
    }
}