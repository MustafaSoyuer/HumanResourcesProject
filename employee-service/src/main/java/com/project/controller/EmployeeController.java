package com.project.controller;

import com.project.dto.request.ManagerUpdateEmployeeRequestDto;
import com.project.dto.request.SaveEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.dto.response.BasicResponse;
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

    /**
     * Employee kayıt etme methodu
     * @param dto
     * @return
     */
    @PostMapping(SAVE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> save(@RequestBody SaveEmployeeRequestDto dto) {
        employeeService.save(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Employee saved successfully")
                .data(true)
                .build()
        );
    }

    /**
     * Employeenin kendi bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(UPDATE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateEmployee(@RequestBody UpdateEmployeeRequestDto dto) {
        employeeService.updateEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Employee updated successfully")
                .data(true)
                .build()
        );
    }
    /**
     * Managerin vey adminin employee bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(MANAGER_UPDATE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> managerUpdateEmployee(@RequestBody ManagerUpdateEmployeeRequestDto dto) {
        employeeService.managerUpdateEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Employee updated successfully")
                .data(true)
                .build()
        );
    }


// email _> ad.soyad@şirketadı.com
}
