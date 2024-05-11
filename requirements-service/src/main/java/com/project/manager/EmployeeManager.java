package com.project.manager;
import com.project.dto.response.EmployeeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.project.utility.constants.RestApiUrls.FIND_EMPLOYEE_BY_TOKEN;

@FeignClient(url= "http://localhost:9094/dev/v1/employee", name= "requirements-employee")
public interface EmployeeManager {

    @GetMapping(FIND_EMPLOYEE_BY_TOKEN)
    ResponseEntity<EmployeeResponseDto> findEmployeeByToken(@RequestParam String token);

    @GetMapping("/find-by-id")
    public ResponseEntity<EmployeeResponseDto> findById(@RequestParam Long id);
}
