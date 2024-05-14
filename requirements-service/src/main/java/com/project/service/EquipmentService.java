package com.project.service;

import com.project.dto.request.BaseRequestForRequirementsDto;
import com.project.dto.request.EquipmentRequestDto;
import com.project.dto.response.EmployeeResponseDto;
import com.project.dto.response.ManagerResponseDto;
import com.project.entity.Equipments;
import com.project.exception.ErrorType;
import com.project.exception.RequirementsServiceException;
import com.project.manager.EmployeeManager;
import com.project.manager.ManagerManager;
import com.project.repository.EquipmentsRepository;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentsRepository equipmentsRepository;
    private final EmployeeManager employeeManager;
    private final ManagerManager managerManager;

    public List<Equipments> findAllMyEquipmentsForEmployee(String token) {
        EmployeeResponseDto responseDto = Optional.ofNullable(employeeManager.findEmployeeByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        List<Equipments> equipmentList= equipmentsRepository.findAllByEmployeeId(responseDto.getId());
        if (equipmentList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.EQUIPMENTS_NOT_FOUND);
        }
        return equipmentList;
    }

    public Boolean requestEquipmentFromEmployee(EquipmentRequestDto dto) {
        EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findEmployeeByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        equipmentsRepository.save(Equipments.builder()
                        .equipmentType(dto.getEquipmentType())
                        .employeeId(employee.getId())
                        .status(EStatus.PENDING)
                        .managerId(employee.getManagerId())
                        .authId(employee.getAuthId())
                        .createDate(System.currentTimeMillis())
                        .document(dto.getDocument())
                        .receivingDate(System.currentTimeMillis())
                        .status(EStatus.PENDING)
                        .name(dto.getName())
                .build());
        return true;
    }

    public Boolean returnEquipmentFromEmployee(BaseRequestForRequirementsDto dto) {
        Optional.ofNullable(employeeManager.findEmployeeByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        Optional<Equipments> optionalEquipment = equipmentsRepository.findOptionalById(dto.getRequirementId());
        if(optionalEquipment.isEmpty()){
            throw new RequirementsServiceException(ErrorType.EQUIPMENTS_NOT_FOUND);
        }
        Equipments equipment = optionalEquipment.get();
        equipment.setStatus(EStatus.PASSIVE);
        equipment.setReturningDate(System.currentTimeMillis());
        equipment.setUpdateDate(System.currentTimeMillis());

        equipmentsRepository.save(equipment);
        return true;
    }

    public List<Equipments> findAllEquipmentsOfEmployee(String token) {

       ManagerResponseDto manager= Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));

        List<Equipments> equipmentList = equipmentsRepository.findAllByManagerId(manager.getId());
        if (equipmentList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.EQUIPMENTS_NOT_FOUND);
        }

        return equipmentList;
    }
}
