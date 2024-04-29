package com.project.controller;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.dto.request.RegisterAdminRequestDto;
import com.project.dto.request.RegisterEmployeeRequestDto;
import com.project.dto.request.RegisterManagerRequestDto;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.dto.response.BasicResponse;
import com.project.dto.response.RegisterEmployeeResponseDto;
import com.project.dto.response.RegisterManagerResponseDto;
import com.project.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.project.constants.RestApiUrls.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<AuthLoginResponseDto>> login(@RequestBody @Valid AuthLoginRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<AuthLoginResponseDto>builder()
                .status(200)
                .message("Login successful")
                .data(authService.login(dto))
                .build());
    }

    @PostMapping(REGISTER_ADMIN)
    @CrossOrigin("*")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BasicResponse<Void>> registerAdmin(@RequestBody @Valid RegisterAdminRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Void>builder()
                .status(200)
                .message("Admin Register successful")
                        .data(authService.registerAdmin(dto))
                .build());
    }

    @PostMapping(REGISTER_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<RegisterManagerResponseDto>> registerManager(@RequestBody @Valid RegisterManagerRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<RegisterManagerResponseDto>builder()
                .status(200)
                .message("Manager Register successful")
                .data(authService.registerManager(dto))
                .build());
    }


    //bu addemployee seklinde olabilir
    @PostMapping(REGISTER_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<RegisterEmployeeResponseDto>> registerEmployee(@RequestBody @Valid RegisterEmployeeRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<RegisterEmployeeResponseDto>builder()
                .status(200)
                .message("Employee Register successful")
                .data(authService.registerEmployee(dto))
                .build());
    }
}
