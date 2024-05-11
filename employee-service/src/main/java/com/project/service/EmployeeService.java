package com.project.service;

import com.project.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.project.dto.request.SaveEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.dto.response.EmployeeResponseDto;
import com.project.dto.request.*;
import com.project.entity.Employee;
import com.project.exception.EmployeeServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.EmployeeMapper;
import com.project.repository.EmployeeRepository;
import com.project.utility.JwtTokenManager;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JwtTokenManager jwtTokenManager;


    public Boolean saveEmployee(SaveEmployeeRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findOptionalByIdentityNumber(dto.getIdentityNumber());
        if (employee.isPresent()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_ALREADY_EXISTS);
        }

        employeeRepository.save(EmployeeMapper.INSTANCE.fromSaveEmployeeRequestDtoToEmployee(dto));
        return true;


    }

    //TODO: Employee bilgilerini guncellerken girmediğim bilgiler null geliyor
    public Boolean updateEmployee(UpdateEmployeeRequestDto dto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(dto.getId());
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(dto.getName());
            employee.setSurname(dto.getSurname());
            employee.setBirthDate(dto.getBirthDate());
            employee.setPhoneNumber(dto.getPhoneNumber());
            employee.setAddress(dto.getAddress());
            employee.setAvatar(dto.getAvatar());
            employee.setUpdateAt(System.currentTimeMillis());
            employeeRepository.save(employee);
            return true;
        }else{
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
    }

    public Boolean managerOrAdminUpdateEmployee(ManagerOrAdminUpdateEmployeeRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findById(dto.getId());
        if (employee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        employee.get().setUpdateAt(System.currentTimeMillis());
        employeeRepository.save(EmployeeMapper.INSTANCE.fromManagerOrAdminUpdateEmployeeRequestDtoToEmployee(dto));

        return true;
    }

    /**
     * Manager employee listesini getirir.
     * @param managerId
     * @return
     */
    public List<Employee> getEmployeesByManagerId(Long managerId) {

        return employeeRepository.findByManagerId(managerId);
    }

    public void activateEmployee(ActivateEmployeeRequestDto dto) {
        Optional<Long> token = jwtTokenManager.getIdFromToken(dto.getToken());
        if (token.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.INVALID_TOKEN);
        }
        Optional<Employee> employee = employeeRepository.findById(dto.getId());
        if (employee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        employee.get().setUpdateAt(System.currentTimeMillis());
        employee.get().setStatus(EStatus.ACTIVE);
        employeeRepository.save(employee.get());
    }


    public EmployeeResponseDto findEmployeeByToken(String token) {
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if(authId.isPresent()){
            Employee employee = employeeRepository.findByAuthId(authId.get()).get();
            return  EmployeeMapper.INSTANCE.fromEmployeeToEmployeeResponseDto(employee);
        }
        throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
    }

    public EmployeeResponseDto findById(Long id) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();
        return EmployeeMapper.INSTANCE.fromEmployeeToEmployeeResponseDto(employee);
    }


//TODO
    /**
     *  String companyEmail = "manager" + dto.getName() + "@" + dto.getCompanyName() + ".com";
     *         auth.setCompanyEmail(companyEmail.toLowerCase());
     *         auth.setUserType(EUserType.MANAGER);
     *         auth.setCompanyName(auth.getCompanyName().toLowerCase());
     *         save(auth);
     */










}
