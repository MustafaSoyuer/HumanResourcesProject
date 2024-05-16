package com.project.service;

import com.project.dto.request.AddShiftRequestDto;
import com.project.dto.response.EmployeeResponseDto;
import com.project.dto.response.ManagerResponseDto;
import com.project.entity.Shift;
import com.project.exception.ErrorType;
import com.project.exception.RequirementsServiceException;
import com.project.manager.EmployeeManager;
import com.project.manager.ManagerManager;
import com.project.repository.ShiftRepository;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final ManagerManager managerManager;
    private final EmployeeManager employeeManager;

    public Boolean addShift(AddShiftRequestDto dto) {
        ManagerResponseDto managerResponseDto = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));
        EmployeeResponseDto employeeResponseDto = Optional.ofNullable(employeeManager.findById(dto.getEmployeeId()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        shiftRepository.save(
                Shift.builder()
                        .managerId(managerResponseDto.getId())
                        .employeeId(employeeResponseDto.getId())
                        .employeeName(employeeResponseDto.getName())
                        .employeeSurname(employeeResponseDto.getSurname())
                        .companyName(employeeResponseDto.getCompanyName())
                        .shiftType(dto.getShiftType())
                        .startTime(dto.getStartTime())
                        .endTime(dto.getEndTime())
                        .status(EStatus.ACTIVE)
                        .createDate(System.currentTimeMillis())
                        .updateDate(System.currentTimeMillis())
                .build());
        return true;
    }

    public List<Shift> getAllShiftsOfEmployees(String token) {
        ManagerResponseDto managerResponseDto =Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));
        Optional<List<Shift>> shiftList = shiftRepository.findAllByManagerId(managerResponseDto.getId());
        if(shiftList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.SHIFTS_NOT_FOUND);
        }
        return shiftList.get();

    }


    public List<Shift> getShiftOfEmployee(String token, Long employeeId) {
        Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));

        Optional<List<Shift>> shiftList = shiftRepository.findAllByEmployeeId(employeeId);
        if(shiftList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.SHIFTS_NOT_FOUND);
        }
        return shiftList.get();
    }

    public List<Shift> getAllMyShifts(String token) {
        EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findEmployeeByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        Optional<List<Shift>> shiftList = shiftRepository.findAllByEmployeeId(employee.getId());
        if(shiftList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.SHIFTS_NOT_FOUND);
        }
        return shiftList.get();
    }
}
