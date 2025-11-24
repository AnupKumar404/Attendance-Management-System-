package com.attendanceApp.controllers;

import com.attendanceApp.dtos.EmailSendDto;
import com.attendanceApp.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class EmailSendController {

    private final EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<CompletableFuture<String>> sendEmailThroughMailSender(@Valid @RequestBody EmailSendDto dto){

        return new ResponseEntity<>(emailService.sendEmail(dto), HttpStatus.OK);
    }
}