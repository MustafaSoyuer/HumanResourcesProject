package com.project.repository;

import com.project.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses,Long> {

    List<Expenses> findAllByEmployeeId(Long id);
}
