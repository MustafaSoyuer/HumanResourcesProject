package com.project.service;

import com.project.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.project.dto.request.AddEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.entity.Employee;
import com.project.exception.EmployeeServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.EmployeeMapper;
import com.project.rabbitmq.model.AddEmployeeModel;
import com.project.rabbitmq.producer.AddEmployeeProducer;
import com.project.repository.EmployeeRepository;
import com.project.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AddEmployeeProducer addEmployeeProducer;

    public Boolean addEmployee(AddEmployeeRequestDto dto) {
        Optional<Employee> optionalEmployee = employeeRepository.findOptionalByIdentityNumber(dto.getIdentityNumber());
        if(optionalEmployee.isPresent()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_ALREADY_EXISTS);
        }

        Employee employee = EmployeeMapper.INSTANCE.fromAddEmployeeRequestDtoToEmployee(dto);
        String name = normalizeAndRemoveSpaces(dto.getName().toLowerCase());
        String surname = normalizeAndRemoveSpaces(dto.getSurname().toLowerCase());
        String companyName = normalizeAndRemoveSpaces(dto.getCompanyName().toLowerCase());

        String employeeEmail = name + "." + surname + "@" + companyName + ".com";



        employee.setEmail(employeeEmail);

        employee.setCreateAt(System.currentTimeMillis());
        employee.setUpdateAt(System.currentTimeMillis());
        employeeRepository.save(employee);
        addEmployeeProducer.sendMessage(AddEmployeeModel.builder()
                        .email(employeeEmail)
                        .name(dto.getName())
                        .surname(dto.getSurname())
                .build());

        return true;
    }

    //TODO: Employee bilgilerini guncellerken girmediğim bilgiler null geliyor
    public Boolean updateEmployee(UpdateEmployeeRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findById(dto.getId());
        if (employee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        employee.get().setUpdateAt(System.currentTimeMillis());

        employeeRepository.save(EmployeeMapper.INSTANCE.fromUpdateEmployeeRequestDtoToEmployee(dto));
        return true;
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

    public String normalizeAndRemoveSpaces(String input) {
        String normalizedString = Normalizer.normalize(input,Normalizer.Form.NFD);
        // Add the following lines to preserve the following characters
        normalizedString = normalizedString.replace("ı","i");
        normalizedString = normalizedString.replace("ö","o");
        normalizedString = normalizedString.replace("ü","u");
        normalizedString = normalizedString.replace("ç","c");
        normalizedString = normalizedString.replace("ş","s");
        normalizedString = normalizedString.replace("ğ","g");

        normalizedString = normalizedString.replaceAll("[^\\p{ASCII}]","");
        normalizedString = normalizedString.replaceAll("\\s+","");

        return normalizedString;
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
