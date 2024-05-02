package com.project.service;

import com.project.dto.request.AddEmployeeRequestDto;
import com.project.dto.request.SaveManagerRequestDto;
import com.project.entity.Manager;
import com.project.exception.ErrorType;
import com.project.exception.ManagerServiceException;
import com.project.mapper.ManagerMapper;
import com.project.rabbitmq.model.CreateEmployeeModel;
import com.project.rabbitmq.model.CreateUserModel;
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

    public Manager save(SaveManagerRequestDto dto) {

        return managerRepository.save(ManagerMapper.INSTANCE.fromSaveManagerRequestDtoToManager(dto));
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
