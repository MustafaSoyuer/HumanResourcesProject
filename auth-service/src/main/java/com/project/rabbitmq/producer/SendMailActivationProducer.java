package com.project.rabbitmq.producer;

import com.project.rabbitmq.model.CreateManagerModel;
import com.project.rabbitmq.model.SendMailActivationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailActivationProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(SendMailActivationModel model){
        rabbitTemplate.convertAndSend("auth-exchange", "send-mail-activation-binding-key", model);
    }
}
