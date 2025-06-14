package com.example.Ecomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail , String name , double amount)
    {
        SimpleMailMessage message =new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("✅ Payment successful  ");
        message.setText("Hi, "+name+ "  \n" +
                "Your order has been placed successfully \n" +
                "Thank you for Shopping from Our Mart ");
        mailSender.send(message);
    }
}
