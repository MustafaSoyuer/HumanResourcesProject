package com.project.controller;

import com.project.dto.request.SaveEmployeeRequestDto;
import com.project.entity.Employee;
import com.project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(SAVE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<Employee> save(@RequestBody SaveEmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.save(dto));
    }

// email _> ad.soyad@şirketadı.com
}
