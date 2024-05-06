package com.project.controller;

import com.project.dto.request.ApproveManagerRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {

    private final AdminService adminService;

    @PostMapping(APPROVE_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> activateCompanyStatus(@RequestBody ApproveManagerRequestDto dto){
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .data(adminService.activateCompanyStatus(dto)).build());

    }



}
