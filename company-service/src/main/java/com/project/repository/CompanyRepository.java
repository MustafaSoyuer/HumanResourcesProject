package com.project.repository;

import com.project.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findOptionalById(Long id);
}
