package com.project.rabbitmq.producer;

import com.project.rabbitmq.model.AddEmployeeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddEmployeeProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(AddEmployeeModel model){
        rabbitTemplate.convertAndSend("employee-exchange", "add-employee-binding-key", model);
    }
}
