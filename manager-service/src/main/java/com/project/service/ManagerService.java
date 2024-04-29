package com.project.service;

import com.project.dto.request.SaveManagerRequestDto;
import com.project.entity.Manager;
import com.project.exception.ErrorType;
import com.project.exception.ManagerServiceException;
import com.project.mapper.ManagerMapper;
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

    public Manager save(SaveManagerRequestDto dto) {
        return managerRepository.save(ManagerMapper.INSTANCE.fromSaveManagerRequestDtoToManager(dto));
    }
}
