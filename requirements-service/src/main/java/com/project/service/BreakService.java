package com.project.service;

import com.project.repository.BreakRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreakService {
    private final BreakRepository breakRepository;
}
