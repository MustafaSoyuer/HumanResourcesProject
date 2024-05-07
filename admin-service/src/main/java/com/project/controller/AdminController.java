package com.project.controller;

import com.project.dto.request.UpdateAdminRequestDto;
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

    /**
     * Admin kendi bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(UPDATE_ADMIN)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateAdmin(@RequestBody UpdateAdminRequestDto dto) {


        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Admin updated successfully")
                .data(adminService.updateAdmin(dto))
                .build()
        );
    }



}
