package com.project.controller;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.service.AuthService;
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
    public ResponseEntity<AuthLoginResponseDto> login(@RequestBody AuthLoginRequestDto dto) {
        return ResponseEntity.ok(new AuthLoginResponseDto(authService.login(dto)));

    }
}
