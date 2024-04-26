package com.project.service;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.entity.Auth;
import com.project.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public String login(AuthLoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        return auth.map(Auth::getEmail).orElse(null);
    }
}
