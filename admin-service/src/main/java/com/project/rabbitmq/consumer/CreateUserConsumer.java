package com.project.rabbitmq.consumer;

import com.project.rabbitmq.model.ActivateCompanyStatusModel;
import com.project.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final AdminService adminService;

    @RabbitListener(queues = "auth-queue")
    public void createUserListener(ActivateCompanyStatusModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);

    }
}
