package com.project.manager;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.dto.response.EmployeeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.utility.constants.RestApiUrls.*;

@FeignClient(url= "http://localhost:9094/dev/v1/employee", name= "requirements-employee")
public interface EmployeeManager {

    @GetMapping(FIND_EMPLOYEE_BY_TOKEN)
    ResponseEntity<EmployeeResponseDto> findEmployeeByToken(@RequestParam String token);

    @GetMapping("/find-by-id")
    ResponseEntity<EmployeeResponseDto> findById(@RequestParam Long id);

    @PutMapping(UPDATE_SALARY_EMPLOYEE)
    ResponseEntity<BasicResponse<EmployeeResponseDto>> updateEmployeeSalary(@RequestParam Long id, @RequestParam Double salary);
}
