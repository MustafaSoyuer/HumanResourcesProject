package com.project.repository;

import com.project.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift,Long> {

    Optional<List<Shift>> findAllByManagerId(Long id);

    Optional<List<Shift>> findAllByEmployeeId(Long employeeId);
}
