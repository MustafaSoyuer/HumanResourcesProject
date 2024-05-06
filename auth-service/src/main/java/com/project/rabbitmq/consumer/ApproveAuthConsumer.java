package com.project.rabbitmq.consumer;

import com.project.rabbitmq.model.ApproveAuthModel;
import com.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveAuthConsumer {

    private final AuthService authService;

    @RabbitListener(queues = "approve-auth-queue")
    public void createUserListener(ApproveAuthModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        authService.approveAuth(model.getAuthId());
    }
}
