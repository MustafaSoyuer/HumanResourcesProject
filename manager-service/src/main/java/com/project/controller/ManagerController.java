package com.project.controller;

import com.project.dto.request.AddEmployeeRequestDto;
import com.project.dto.request.AdminUpdateManagerRequestDto;
import com.project.dto.request.SaveManagerRequestDto;
import com.project.dto.request.UpdateManagerRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.dto.response.SaveManagerResponseDto;
import com.project.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.constants.RestApiUrls.*;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping(SAVE_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<SaveManagerResponseDto>> createManager(@RequestBody SaveManagerRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<SaveManagerResponseDto>builder()
                .status(200)
                .message("Manager created")
                .data(managerService.createManager(dto))
                .build());
    }

    @PostMapping(ADD_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> addEmployee(@RequestBody AddEmployeeRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Employee added")
                .data(managerService.addEmployee(dto))
                .build());
    }

    /**
     * Manager kendi bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(UPDATE_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateManager(@RequestBody UpdateManagerRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Manager updated successfully")
                .data(managerService.updateManager(dto))
                .build()
        );
    }

    /**
     * Admin manager bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(ADMIN_UPDATE_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> adminUpdateManager(@RequestBody AdminUpdateManagerRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Manager updated successfully")
                .data(managerService.adminUpdateManager(dto))
                .build()
        );
    }





}
