package com.project.rabbitmq.consumer;


import com.project.rabbitmq.model.SendMailActivationModel;
import com.project.rabbitmq.model.SendMailRejectModel;
import com.project.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailRejectConsumer {

    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "send-mail-reject-queue")
    public void createUserListener(SendMailRejectModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        mailSenderService.sendMailReject(model.getEmail());
    }
}
