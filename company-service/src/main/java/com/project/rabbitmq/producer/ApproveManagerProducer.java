package com.project.rabbitmq.producer;

import com.project.rabbitmq.model.ApproveManagerModel;
import com.project.rabbitmq.model.CreateCompanyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveManagerProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(ApproveManagerModel model){
        rabbitTemplate.convertAndSend("company-exchange", "approve-manager-binding-key", model);
    }
}
