package com.project.repository;

import com.project.entity.Equipments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentsRepository extends JpaRepository<Equipments,Long> {
    List<Equipments> findAllByEmployeeId(Long employeeId);


    Optional<Equipments> findOptionalById(Long id);

}
