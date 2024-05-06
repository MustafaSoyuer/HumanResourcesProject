package com.project.rabbitmq.producer;


import com.project.rabbitmq.model.ApproveAuthModel;
import com.project.rabbitmq.model.RejectAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectAuthProducer {

    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(RejectAuthModel model){
        rabbitTemplate.convertAndSend("manager-exchange", "reject-auth-binding-key", model);
    }
}
