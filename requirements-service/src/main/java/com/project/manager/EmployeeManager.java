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

    @GetMapping("/find-employee-by-token")
    @CrossOrigin("*")
    ResponseEntity<EmployeeResponseDto> findEmployeeByToken(@RequestParam("token") String token);

    @GetMapping("/find-by-id")
    @CrossOrigin("*")
    ResponseEntity<EmployeeResponseDto> findById(@RequestParam("id") Long id);

    @PutMapping("/update-salary-employee")
    @CrossOrigin("*")
    ResponseEntity<BasicResponse<EmployeeResponseDto>> updateEmployeeSalary(@RequestParam("id")Long id, @RequestParam("salary") Double salary);
}
