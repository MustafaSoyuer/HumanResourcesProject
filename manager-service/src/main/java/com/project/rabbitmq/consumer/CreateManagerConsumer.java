package com.project.rabbitmq.consumer;

import com.project.dto.request.SaveManagerRequestDto;
import com.project.rabbitmq.model.CreateManagerModel;
import com.project.service.ManagerService;
import com.project.utility.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateManagerConsumer {

    private final ManagerService managerService;

    @RabbitListener(queues = "auth-create-manager-queue")
    public void createUserListener(CreateManagerModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        managerService.createManager(SaveManagerRequestDto.builder()
                        .authId(model.getAuthId())
                        .email(model.getEmail())
                        .taxNumber(model.getTaxNumber())
                        .phone(model.getPhone())
                        .address(model.getAddress())
                        .company(model.getCompany())
                        .name(model.getName())
                        .surname(model.getSurname())
                        .role(ERole.MANAGER)
                        .createAt(System.currentTimeMillis())
                        .status(model.getStatus())
                        .build());


    }
}
