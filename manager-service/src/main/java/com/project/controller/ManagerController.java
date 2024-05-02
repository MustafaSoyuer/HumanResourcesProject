package com.project.controller;

import com.project.dto.request.AddEmployeeRequestDto;
import com.project.dto.request.SaveManagerRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.entity.Manager;
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
    public ResponseEntity<Manager> save(@RequestBody SaveManagerRequestDto dto) {
        return ResponseEntity.ok(managerService.save(dto));
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


}
