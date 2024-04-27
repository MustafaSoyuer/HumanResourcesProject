package com.project.controller;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.dto.response.BasicResponse;
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
                .message("Giriş Başarılı")
                .data(authService.login(dto))
                .build());
    }
}
