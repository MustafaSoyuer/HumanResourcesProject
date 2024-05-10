package com.project.controller;

import com.project.dto.request.GetEmployeesByManagerIdRequestDto;
import com.project.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.dto.response.EmployeeResponseDto;
import com.project.entity.Employee;
import com.project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Employeenin kendi bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(UPDATE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateEmployee(@RequestBody UpdateEmployeeRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Employee updated successfully")
                .data(employeeService.updateEmployee(dto))
                .build()
        );
    }
    /**
     * Managerin vey adminin employee bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(MANAGER_OR_ADMIN_UPDATE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> managerOrAdminUpdateEmployee(@RequestBody ManagerOrAdminUpdateEmployeeRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Manager updated to employee successfully")
                .data(employeeService.managerOrAdminUpdateEmployee(dto))
                .build()
        );
    }

    @GetMapping(GET_EMPLOYEES_BY_MANAGER_ID)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Employee>>> getEmployeesByManagerId(Long managerId) {

        return ResponseEntity.ok(BasicResponse.<List<Employee>>builder()
                .status(200)
                .message("Employees list turned successfully")
                .data(employeeService.getEmployeesByManagerId(managerId))
                .build()
        );
    }

    @GetMapping(FIND_BY_TOKEN)
    @CrossOrigin("*")
    public ResponseEntity<EmployeeResponseDto> findUserByToken(@RequestParam String token) {
        return ResponseEntity.ok(employeeService.findEmployeeByToken(token));
    }




// email _> ad.soyad@şirketadı.com
}
