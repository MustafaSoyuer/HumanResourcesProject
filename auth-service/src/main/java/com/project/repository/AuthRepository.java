package com.project.repository;

import com.project.entity.Auth;
import com.project.utility.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findOptionalByEmailAndPassword(String email, String password);

    Optional<Auth> findOptionalById(Long id);

    Optional<Auth> findOptionalByEmail(String email);

    List<Auth> findByRole(ERole role);

}
