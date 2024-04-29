package com.project.service;

import com.project.dto.request.SaveManagerRequestDto;
import com.project.exception.ErrorType;
import com.project.exception.ManagerServiceException;
import com.project.repository.ManagerRepository;
import com.project.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final JwtTokenManager jwtTokenManager;

    public Boolean save(SaveManagerRequestDto dto) {
        return true;
    }
}
