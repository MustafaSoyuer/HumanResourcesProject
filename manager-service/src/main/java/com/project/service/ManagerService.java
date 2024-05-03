package com.project.service;

import com.project.dto.request.AddEmployeeRequestDto;
import com.project.dto.request.SaveManagerRequestDto;
import com.project.dto.response.SaveManagerResponseDto;
import com.project.entity.Manager;
import com.project.exception.ErrorType;
import com.project.exception.ManagerServiceException;
import com.project.mapper.ManagerMapper;
import com.project.rabbitmq.model.CreateCompanyModel;
import com.project.rabbitmq.model.CreateEmployeeModel;
import com.project.rabbitmq.producer.CreateCompanyProducer;
import com.project.rabbitmq.producer.CreateEmployeeProducer;
import com.project.repository.ManagerRepository;
import com.project.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateEmployeeProducer createEmployeeProducer;
    private final CreateCompanyProducer createCompanyProducer;

    public SaveManagerResponseDto createManager(SaveManagerRequestDto dto) {
        Manager manager= managerRepository.save(ManagerMapper.INSTANCE.fromSaveManagerRequestDtoToManager(dto));
        createCompanyProducer.sendMessage(CreateCompanyModel.builder()
                        .managerId(manager.getId())
                        .name(dto.getCompany())
                        .taxNumber(dto.getTaxNumber())
                        .status(dto.getStatus())
                .build());
        return ManagerMapper.INSTANCE.fromManagerToSaveManagerResponseDto(manager);
    }

    public Boolean addEmployee(AddEmployeeRequestDto dto) {

        Optional<Manager> manager = managerRepository.findOptionalById(dto.getManagerId());

        if (manager.isEmpty()) {
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        }else {

            createEmployeeProducer.sendMessage(CreateEmployeeModel.builder()
                    .managerId(manager.get().getId())
                    .name(dto.getName())
                    .surname(dto.getSurname())
                    .identityNumber(dto.getIdentityNumber())
                    .phoneNumber(dto.getPhoneNumber())
                    .address(dto.getAddress())
                    .position(dto.getPosition())
                    .department(dto.getDepartment())
                    .occupation(dto.getOccupation())
                    .build());
            return true;
        }


    }
}
