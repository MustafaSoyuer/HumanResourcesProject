package com.project.rabbitmq.consumer;

import com.project.dto.request.CompanyCreateRequestDto;
import com.project.rabbitmq.model.CreateCompanyModel;
import com.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompanyConsumer {
    private final CompanyService companyService;

    @RabbitListener(queues = "create-company-queue")
    public void createEmployeeListener(CreateCompanyModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        companyService.createCompany(CompanyCreateRequestDto.builder()
                        .managerId(model.getManagerId())
                        .name(model.getName())
                        .taxNumber(model.getTaxNumber())
                        .status(model.getStatus())
                        .createAt(System.currentTimeMillis())
                .build());
    }
}
