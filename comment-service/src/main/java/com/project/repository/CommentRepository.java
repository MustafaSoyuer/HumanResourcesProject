package com.project.repository;

import com.project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findOptionalById(Long id);

    Comment findByManagerId(Long managerId);

    Comment findByToken(String token);
}
