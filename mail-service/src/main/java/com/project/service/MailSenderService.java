package com.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    /**
     * ttdt sjkq xora nmhr
     */
    private final JavaMailSender javaMailSender;

    @EventListener(ApplicationReadyEvent.class)
    public void sendMailChangePassword(String email, String activationCode) {
        String activationLink = generateActivationLink(email, activationCode);
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("workforce.solutions.info@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("Workforce Solutions");
        mailMessage.setText("Hesabınız aktifleştirildi. \n" + activationLink);

        javaMailSender.send(mailMessage);
    }

    public String generateActivationLink(String email, String activationCode) {
        return "http://localhost:9091/dev/v1/auth/change-password?email=" + email + "&activationCode=" + activationCode;
        //TODO: localhost degisecek
    }


}
