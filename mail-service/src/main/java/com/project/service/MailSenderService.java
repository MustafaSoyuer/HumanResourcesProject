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
    public void sendMail() {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("workforce.solutions.info@gmail.com");
        mailMessage.setTo("workforce.solutions.info@gmail.com");
        mailMessage.setSubject("Aktivasyon Kodunuz...");
        mailMessage.setText("email ile doğrulama");
//        mailMessage.setText( "Kullan�c� ad�n�z: " + dto.getUsername() + "\n" +
//                "Hesab�n�z� aktifle�tirmek i�in l�tfen a�a��daki linke t�klay�n:\n" +
//                "http://34.155.167.124:9090/dev/v1/auth/activate-status/" + dto.getActivationCode());

        javaMailSender.send(mailMessage);
    }


}
