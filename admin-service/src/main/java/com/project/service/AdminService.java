package com.project.service;

import com.project.dto.response.BasicResponse;
import com.project.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Boolean approveManager(Long authId) {

        return true;
    }



}
