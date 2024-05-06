package com.project.rabbitmq.consumer;

import com.project.rabbitmq.model.ApproveManagerModel;
import com.project.rabbitmq.model.RejectManagerModel;
import com.project.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectManagerConsumer {

    private final ManagerService managerService;

    @RabbitListener(queues = "reject-manager-queue")
    public void createUserListener(RejectManagerModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        managerService.rejectManager(model.getManagerId());
    }
}
