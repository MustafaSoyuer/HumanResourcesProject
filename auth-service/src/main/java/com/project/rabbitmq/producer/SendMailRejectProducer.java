package com.project.rabbitmq.producer;

import com.project.rabbitmq.model.SendMailActivationModel;
import com.project.rabbitmq.model.SendMailRejectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailRejectProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(SendMailRejectModel model){
        rabbitTemplate.convertAndSend("auth-exchange", "send-mail-reject-binding-key", model);
    }
}
