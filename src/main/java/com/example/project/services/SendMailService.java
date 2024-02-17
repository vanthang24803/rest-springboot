package com.example.project.services;

public interface SendMailService {
    void sendMail(String toEmail, String subject, String body);
}
