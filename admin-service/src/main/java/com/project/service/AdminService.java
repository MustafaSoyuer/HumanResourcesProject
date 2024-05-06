package com.project.service;

import com.project.dto.request.ApproveManagerRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.entity.Admin;
import com.project.exception.AdminServiceException;
import com.project.exception.ErrorType;
import com.project.repository.AdminRepository;
import com.project.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final JwtTokenManager jwtTokenManager;

    public Boolean activateCompanyStatus(ApproveManagerRequestDto dto) {
        Optional<Long> authId= jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty())
            throw new AdminServiceException(ErrorType.INVALID_TOKEN);


        return true;
    }



}
