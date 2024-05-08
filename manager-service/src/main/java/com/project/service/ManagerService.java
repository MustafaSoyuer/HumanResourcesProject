package com.project.service;

import com.project.dto.request.AddEmployeeRequestDto;
import com.project.dto.request.AdminUpdateManagerRequestDto;
import com.project.dto.request.SaveManagerRequestDto;
import com.project.dto.request.UpdateManagerRequestDto;
import com.project.dto.response.SaveManagerResponseDto;
import com.project.entity.Manager;
import com.project.exception.ErrorType;
import com.project.exception.ManagerServiceException;
import com.project.mapper.ManagerMapper;
import com.project.rabbitmq.model.*;
import com.project.rabbitmq.producer.ApproveAuthProducer;
import com.project.rabbitmq.producer.CreateCompanyProducer;
import com.project.rabbitmq.producer.CreateEmployeeProducer;
import com.project.rabbitmq.producer.RejectAuthProducer;
import com.project.repository.ManagerRepository;
import com.project.utility.JwtTokenManager;
import com.project.utility.enums.EStatus;
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
    private final ApproveAuthProducer approveAuthProducer;
    private final RejectAuthProducer rejectAuthProducer;

    public SaveManagerResponseDto createManager(SaveManagerRequestDto dto) {
        Manager manager= ManagerMapper.INSTANCE.fromSaveManagerRequestDtoToManager(dto);
        manager.setUpdateAt(System.currentTimeMillis());
        managerRepository.save(manager);
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

    public Boolean approveManager(Long managerId) {
        Optional<Manager> optionalManager= managerRepository.findById(managerId);
        if (optionalManager.isEmpty())
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        Manager manager=optionalManager.get();
        manager.setStatus(EStatus.ACTIVE);
        manager.setUpdateAt(System.currentTimeMillis());
        managerRepository.save(optionalManager.get());
        approveAuthProducer.sendMessage(ApproveAuthModel.builder()
                        .id(manager.getId())
                        .authId(manager.getAuthId())
                        .updateAt(System.currentTimeMillis())
                        .status(manager.getStatus())
                .build());
        return true;
    }

    public Boolean rejectManager(Long managerId) {
        Optional<Manager> optionalManager= managerRepository.findById(managerId);
        if (optionalManager.isEmpty())
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        Manager manager=optionalManager.get();
        manager.setStatus(EStatus.PASSIVE);
        manager.setUpdateAt(System.currentTimeMillis());
        managerRepository.save(optionalManager.get());
        rejectAuthProducer.sendMessage(RejectAuthModel.builder()
                        .id(manager.getId())
                        .authId(manager.getAuthId())
                        .updateAt(System.currentTimeMillis())
                        .status(manager.getStatus())
                .build());
        return true;
    }

    public Boolean updateManager(UpdateManagerRequestDto dto) {
        Optional<Manager> manager = managerRepository.findById(dto.getId());
        if (manager.isEmpty()) {
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        }
        manager.get().setUpdateAt(System.currentTimeMillis());
        managerRepository.save(ManagerMapper.INSTANCE.fromUpdateManagerRequestDtoToManager(dto));
        return true;
    }

    public Boolean adminUpdateManager(AdminUpdateManagerRequestDto dto) {
        Optional<Manager> manager = managerRepository.findById(dto.getId());
        if (manager.isEmpty()) {
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        }
        manager.get().setUpdateAt(System.currentTimeMillis());
        managerRepository.save(ManagerMapper.INSTANCE.fromAdminUpdateManagerRequestDtoToManager(dto));
        return true;
    }


}
