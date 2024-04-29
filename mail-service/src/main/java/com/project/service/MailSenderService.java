package com.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendMail(String email) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("burcusekmen6@gmail.com");
        mailMessage.setTo("burcusekmen6@gmail.com");
        mailMessage.setSubject("Aktivasyon Kodu");

//        mailMessage.setText( "Kullanýcý adýnýz: " + dto.getUsername() + "\n" +
//                "Hesabýnýzý aktifleþtirmek için lütfen aþaðýdaki linke týklayýn:\n" +
//                "http://34.155.167.124:9090/dev/v1/auth/activate-status/" + dto.getActivationCode());

        javaMailSender.send(mailMessage);
    }


}
