package com.project.controller;
import com.project.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EQUIPMENTS)
public class EquipmentsController {
    private final EquipmentService equipmentService;
}
