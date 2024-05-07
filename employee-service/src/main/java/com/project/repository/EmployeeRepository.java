package com.project.repository;

import com.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findOptionalById(Long id);

    Optional<Employee> findOptionalByIdientityNumber(String identityNumber);


}
