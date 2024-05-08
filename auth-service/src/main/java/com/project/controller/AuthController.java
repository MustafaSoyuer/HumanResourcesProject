package com.project.controller;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.dto.request.ChangePasswordDto;
import com.project.dto.request.RegisterAdminRequestDto;
import com.project.dto.request.RegisterManagerRequestDto;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.dto.response.BasicResponse;
import com.project.dto.response.RegisterManagerResponseDto;
import com.project.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
//    @PreAuthorize("hasAuthority-hasRole???('ADMIN')")
    public ResponseEntity<BasicResponse<Boolean>> registerAdmin(@RequestBody @Valid RegisterAdminRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Admin Register successful")
                .data(authService.registerAdmin(dto))
                .build());
    }

    @PostMapping(REGISTER_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> registerManager(@RequestBody @Valid RegisterManagerRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Manager Register successful")
                .data(authService.registerManager(dto))
                .build());
    }

    @PutMapping(CHANGE_PASSWORD)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> changePassword(@RequestBody @Valid ChangePasswordDto dto) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Password changed successfully")
                .data(authService.changePassword(dto))
                .build());
    }



}
