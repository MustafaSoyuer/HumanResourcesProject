package com.project.repository;

import com.project.entity.Leave;
import com.project.utility.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends JpaRepository<Leave,Long> {
    Optional<List<Leave>> findAllByEmployeeId(Long employeeId);

    Optional<List<Leave>> findAllByManagerIdAndStatus(Long managerId, EStatus eStatus);

    Optional<List<Leave>> findOptionalByAuthId(Long authId);
}
