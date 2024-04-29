package com.project.rabbitmq.consumer;

import com.project.dto.request.SaveManagerRequestDto;
import com.project.rabbitmq.model.CreateUserModel;
import com.project.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final ManagerService managerService;

    @RabbitListener(queues = "auth-queue")
    public void createUserListener(CreateUserModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        managerService.save(SaveManagerRequestDto.builder()
                        .authId(model.getAuthId())
                        .email(model.getEmail())
                        .taxNo(model.getTaxNo())
                        .phone(model.getPhone())
                        .address(model.getAddress())
                        .company(model.getCompany())
                        .name(model.getName())
                        .surname(model.getSurname())
                        .build());


    }
}
