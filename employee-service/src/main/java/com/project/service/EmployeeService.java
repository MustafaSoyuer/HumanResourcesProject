package com.project.service;

import com.project.dto.request.ManagerUpdateEmployeeRequestDto;
import com.project.dto.request.SaveEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.entity.Employee;
import com.project.exception.EmployeeServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.EmployeeMapper;
import com.project.repository.EmployeeRepository;
import com.project.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JwtTokenManager jwtTokenManager;

    public void save(SaveEmployeeRequestDto dto) {
        employeeRepository.save(EmployeeMapper.INSTANCE.fromSaveEmployeeRequestDtoToEmployee(dto));
    }

    public void updateEmployee(UpdateEmployeeRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findById(dto.getEmployeeId());
        if (employee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        employeeRepository.save(EmployeeMapper.INSTANCE.fromUpdateEmployeeRequestDtoToEmployee(dto));
    }

    public void managerUpdateEmployee(ManagerUpdateEmployeeRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findById(dto.getEmployeeId());
        if (employee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        employeeRepository.save(EmployeeMapper.INSTANCE.fromManagerUpdateEmployeeRequestDtoToEmployee(dto));
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
