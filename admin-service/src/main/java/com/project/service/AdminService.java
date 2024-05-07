package com.project.service;

import com.project.dto.request.UpdateAdminRequestDto;
import com.project.entity.Admin;
import com.project.exception.AdminServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.AdminMapper;
import com.project.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Boolean updateAdmin(UpdateAdminRequestDto dto) {
        Optional<Admin> admin = adminRepository.findById(dto.getId());
        if (admin.isEmpty()) {
            throw new AdminServiceException(ErrorType.ADMIN_NOT_FOUND);
        }
        admin.get().setUpdateAt(System.currentTimeMillis());
        adminRepository.save(AdminMapper.INSTANCE.fromUpdateAdminRequestDtoToAdmin(dto));
        return true;
    }





}
