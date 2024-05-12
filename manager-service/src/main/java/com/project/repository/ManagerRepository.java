package com.project.repository;

import com.project.dto.request.SaveManagerRequestDto;
import com.project.dto.response.SaveManagerResponseDto;
import com.project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findOptionalById(Long managerId);

    Optional<Manager> findByAuthId(Long aLong);
}
