package com.project.service;

import com.project.repository.EquipmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentsRepository equipmentsRepository;
}
