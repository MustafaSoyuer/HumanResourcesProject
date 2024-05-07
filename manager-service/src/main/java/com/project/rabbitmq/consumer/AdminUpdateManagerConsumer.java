package com.project.rabbitmq.consumer;

import com.project.dto.request.AdminUpdateManagerRequestDto;
import com.project.rabbitmq.model.AdminUpdateManagerModel;
import com.project.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUpdateManagerConsumer {
    private final ManagerService managerService;

    @RabbitListener(queues = "admin-update-manager-queue")
    public void updateManagerListener(AdminUpdateManagerModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        managerService.adminUpdateManager(AdminUpdateManagerRequestDto.builder()
                .id(model.getId())
                .name(model.getName())
                .surname(model.getSurname())
                .avatar(model.getAvatar())
                .birthDate(model.getBirthDate())
                .phone(model.getPhone())
                .email(model.getEmail())
                .password(model.getPassword())
                .address(model.getAddress())
                .occupation(model.getOccupation())
                .companyId(model.getCompanyId())
                .gender(model.getGender())
                .jobStartDate(model.getJobStartDate())
                .updateAt(model.getUpdateAt())
                .status(model.getStatus())
                .build());
    }
}
