package com.project.controller;

import com.project.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.project.dto.request.AddEmployeeRequestDto;
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
    //TODO: identity number unique mi kontrol et
    @PostMapping(ADD_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> addEmployee(@RequestBody AddEmployeeRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Employee saved successfully")
                .data(employeeService.addEmployee(dto))
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


// email _> ad.soyad@şirketadı.com
}
