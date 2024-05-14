package com.project.service;

import com.project.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.project.dto.request.SaveEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.dto.response.EmployeeResponseDto;
import com.project.dto.request.*;
import com.project.dto.response.ManagerResponseDto;
import com.project.entity.Employee;
import com.project.exception.EmployeeServiceException;
import com.project.exception.ErrorType;
import com.project.manager.ManagerManager;
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
    private final ManagerManager managerManager;


    public EmployeeResponseDto saveEmployee(SaveEmployeeRequestDto dto) {
        Optional<Employee> optionalEmployee = employeeRepository.findOptionalByIdentityNumber(dto.getIdentityNumber());
        if (optionalEmployee.isPresent()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_ALREADY_EXISTS);
        }
        Employee employee= employeeRepository.save(EmployeeMapper.INSTANCE.fromSaveEmployeeRequestDtoToEmployee(dto));
        return EmployeeMapper.INSTANCE.fromEmployeeToEmployeeResponseDto(employee);
    }

    //TODO: Employee bilgilerini guncellerken girmediğim bilgiler null geliyor
    public Boolean updateEmployee(UpdateEmployeeRequestDto dto) {
        EmployeeResponseDto employeeResponseDto = Optional.ofNullable(findEmployeeByToken(dto.getToken()))
                .orElseThrow(() -> new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND));
        if(!dto.getId().equals(employeeResponseDto.getId())){
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }

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

    //TODO: token doğrulaması yapılacak
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

    public Boolean activateEmployee(ActivateEmployeeRequestDto dto) {
        ManagerResponseDto managerResponseDto = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new EmployeeServiceException(ErrorType.MANAGER_NOT_FOUND));

        Optional<Employee> employee = employeeRepository.findById(dto.getId());
        if(employee.get().getManagerId().equals(managerResponseDto.getId())) {
            if (employee.isEmpty()) {
                throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
            }
            employee.get().setStatus(EStatus.ACTIVE);
            employee.get().setUpdateAt(System.currentTimeMillis());
            employeeRepository.save(employee.get());
            return true;
        }else {
            throw new EmployeeServiceException(ErrorType.MANAGER_NOT_FOUND);
        }

    }

    public Boolean deactivateEmployee(ActivateEmployeeRequestDto dto) {
        ManagerResponseDto managerResponseDto = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new EmployeeServiceException(ErrorType.MANAGER_NOT_FOUND));

        Optional<Employee> employee = employeeRepository.findById(dto.getId());
        if(employee.get().getManagerId().equals(managerResponseDto.getId())) {
            if (employee.isEmpty()) {
                throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
            }
            employee.get().setStatus(EStatus.PASSIVE);
            employee.get().setUpdateAt(System.currentTimeMillis());
            employeeRepository.save(employee.get());
            return true;
        }else {
            throw new EmployeeServiceException(ErrorType.MANAGER_NOT_FOUND);
        }

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

    public EmployeeResponseDto updateEmployeeSalary(Long id, Double salary) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();
        employee.setUpdateAt(System.currentTimeMillis());
        employee.setSalary(salary);
        employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.fromEmployeeToEmployeeResponseDto(employee);
    }













}
