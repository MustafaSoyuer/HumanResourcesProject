package com.project.rabbitmq.producer;

import com.project.rabbitmq.model.CreateManagerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateManagerProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(CreateManagerModel model){
        rabbitTemplate.convertAndSend("auth-exchange", "auth-create-manager-binding-key", model);
    }
}
