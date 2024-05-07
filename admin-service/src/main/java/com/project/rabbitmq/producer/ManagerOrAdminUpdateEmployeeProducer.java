package com.project.rabbitmq.producer;

import com.project.rabbitmq.model.ManagerOrAdminUpdateEmployeeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerOrAdminUpdateEmployeeProducer {
    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(ManagerOrAdminUpdateEmployeeModel model){
        rabbitTemplate.convertAndSend("admin-exchange", "manager-or-admin-update-employee-binding-key", model);
    }
}
