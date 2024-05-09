package com.project.rabbitmq.producer;

import com.project.rabbitmq.model.CreateEmployeeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(CreateEmployeeModel model){
        rabbitTemplate.convertAndSend("auth-exchange", "auth-create-employee-binding-key", model);
    }
}
