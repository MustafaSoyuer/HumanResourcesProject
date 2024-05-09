package com.project.rabbitmq.producer;

import com.project.rabbitmq.model.GetEmployeesByManagerIdModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEmployeesByManagerIdProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(GetEmployeesByManagerIdModel model) {
        rabbitTemplate.convertAndSend("manager-exchange", "get-employees-by-manager-id-binding-key", model);
    }

}
