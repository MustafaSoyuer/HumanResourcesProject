package com.project.rabbitmq.producer;


import com.project.rabbitmq.model.CreateCompanyModel;
import lombok.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompanyProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(CreateCompanyModel model){
        rabbitTemplate.convertAndSend("manager-exchange", "create-company-binding-key", model);
    }
}
