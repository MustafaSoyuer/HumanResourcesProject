package com.project.service;

import com.project.dto.request.*;
import com.project.dto.response.BaseLeaveResponseDto;
import com.project.entity.Leave;
import com.project.exception.ErrorType;
import com.project.exception.RequirementsServiceException;
import com.project.mapper.LeaveMapper;
import com.project.repository.LeaveRepository;
import com.project.utility.JwtTokenManager;
import com.project.utility.LeaveCalculator;
import com.project.utility.enums.ELeaveType;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final JwtTokenManager jwtTokenManager;
    private final LeaveCalculator leaveCalculator;
    /**
     * TODO: Bu metotlarda token authId buluyor ya, yapılan örnekler değişken baya
     * mesela authId ile managerIdsini bulup işlem yapılan metotlar var genelde dto ile ıd almıyorlar
     * ama authId'yi managerId olarak kabul mü etmiş oluyorlar onu tam anlamadım, işlem yapan kişi için
     * nasıl repositoryden kişiyi bulduracağız vs..
     * @param dto
     * @return
     */

    public Boolean addLeaveToEmployee(AddLeaveRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsServiceException(ErrorType.INVALID_TOKEN);
        }
        /**
         * auhtId'den managerId bulabilecek bir metot ()
         */
         leaveRepository.save(
                Leave.builder()
                        .managerId(dto.getManagerId())
                        .employeeId(dto.getEmployeeId())
                        .approvalDate(System.currentTimeMillis())
                        .startDate(dto.getStartDate())
                        .endDate(dto.getEndDate())
                        .status(EStatus.APPROVED) //Müdür eklediği için otomatik onaylı gitmeli
                .build());
         return true;
    }

    public Boolean approveLeaveForEmployee(BaseRequestForRequirementsDto dto) {
        Optional<Leave> optionalLeave = leaveRepository.findById(dto.getRequirementId());
        if (optionalLeave.isEmpty()) {
            throw new RequirementsServiceException(ErrorType.LEAVE_NOT_FOUND);
        }
        Leave leave = optionalLeave.get();
        leave.setStatus(EStatus.APPROVED);
        leave.setApprovalDate(System.currentTimeMillis());
        return true;
    }

    public Boolean rejectLeaveForEmployee(RejectLeaveRequestDto dto) {
        Optional<Leave> leave = leaveRepository.findById(dto.getLeaveId());
        if (leave.isEmpty()) {
            throw new RequirementsServiceException(ErrorType.LEAVE_NOT_FOUND);
        }
        leave.get().setStatus(EStatus.REJECTED);
        leave.get().setUpdateDate(System.currentTimeMillis());
        leaveRepository.save(leave.get());
        return true;
    }

    public List<BaseLeaveResponseDto> findAllLeavesOfAnEmployee(BaseRequestForEmployeeDto dto) {
        Optional<Long> authId=  jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsServiceException(ErrorType.INVALID_TOKEN);
        }
        Optional<List<Leave>> leaveList = leaveRepository.findAllByEmployeeId(dto.getEmployeeId());
        if(leaveList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.NO_LEAVES_FOR_AN_EMPLOYEE);
        }
        List<BaseLeaveResponseDto> dtos = new ArrayList<>();
        for (Leave leave: leaveList.get()) {
            dtos.add(LeaveMapper.INSTANCE.toResponseDto(leave));
        }
        if (dtos.isEmpty()){
            throw new RequirementsServiceException(ErrorType.LEAVE_NOT_FOUND);
        }
        return dtos;
    }


    public List<BaseLeaveResponseDto> findAllPendingLeavesOfEmployees(BaseRequestPendingLeavesDto dto) {
        Optional<Long> authId=  jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsServiceException(ErrorType.INVALID_TOKEN);
        }
        Optional<List<Leave>> leaveList = leaveRepository.findAllByManagerIdAndStatus(authId.get(), EStatus.PENDING);
        if(leaveList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.NO_PENDING_LEAVES_FOR_MANAGER);
        }
        List<BaseLeaveResponseDto> dtos = new ArrayList<>();
        for (Leave leave: leaveList.get()) {
            dtos.add(LeaveMapper.INSTANCE.toResponseDto(leave));
        }
        if (dtos.isEmpty()){
            throw new RequirementsServiceException(ErrorType.LEAVE_NOT_FOUND);
        }
        return dtos;
    }

    public List<BaseLeaveResponseDto> findAllMyLeavesForEmployee(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsServiceException(ErrorType.INVALID_TOKEN);
        }
        System.out.println("managerin auth id si olması lazım ");
        Optional<List<Leave>> leaves= leaveRepository.findOptionalByAuthId(authId.get());
        if (leaves.isEmpty()){
            throw new RequirementsServiceException(ErrorType.LEAVE_NOT_FOUND);
        }
        List<BaseLeaveResponseDto> dtos= new ArrayList<>();
        for (Leave leave: leaves.get()){
            dtos.add(LeaveMapper.INSTANCE.toResponseDto(leave));
        }
        return dtos;
    }

    //TODO: Bu metodu beraber inceleyebiliriz hem gün hesabı hem belli enum seçebilmesi için yazdım
    public Boolean requestLeaveFromEmployee(RequestLeaveDto dto) {
        Optional<Long> authId=  jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        Double numberOfDays = leaveCalculator.calculateNumberOfDays(dto.getStartDate(), dto.getEndDate());
        if(numberOfDays==0){
            throw new RequirementsServiceException(ErrorType.LEAVE_DATE_NOT_VALID);
        }
        String leaveTypeString = dto.getLeaveType().toString();
        ELeaveType leaveType = ELeaveType.valueOf(leaveTypeString);
        leaveRepository.save(Leave.builder()
                .leaveType(leaveType)
                .employeeId(authId.get())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .numberOfDays(numberOfDays)
                .createDate(System.currentTimeMillis())
                .status(EStatus.PENDING)
                .build());
        return true;
    }
}
