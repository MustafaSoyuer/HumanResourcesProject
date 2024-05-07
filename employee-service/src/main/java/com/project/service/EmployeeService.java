package com.project.service;

import com.project.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.project.dto.request.AddEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.entity.Employee;
import com.project.exception.EmployeeServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.EmployeeMapper;
import com.project.repository.EmployeeRepository;
import com.project.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JwtTokenManager jwtTokenManager;

    //TODO employee email otomatik olusturulacak orn: umit@bilgeadam.com, email olustulduktan sonra mail gidecek

    public Boolean addEmployee(AddEmployeeRequestDto dto) {
        employeeRepository.save(EmployeeMapper.INSTANCE.fromAddEmployeeRequestDtoToEmployee(dto));
        return true;
    }

    public Boolean updateEmployee(UpdateEmployeeRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findById(dto.getEmployeeId());
        if (employee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        employeeRepository.save(EmployeeMapper.INSTANCE.fromUpdateEmployeeRequestDtoToEmployee(dto));
        return true;
    }

    public Boolean managerOrAdminUpdateEmployee(ManagerOrAdminUpdateEmployeeRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findById(dto.getId());
        if (employee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        employeeRepository.save(EmployeeMapper.INSTANCE.fromManagerOrAdminUpdateEmployeeRequestDtoToEmployee(dto));
        return true;
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
