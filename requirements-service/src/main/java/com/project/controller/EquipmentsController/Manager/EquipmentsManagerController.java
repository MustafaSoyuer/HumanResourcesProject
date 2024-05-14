package com.project.controller.EquipmentsController.Manager;

import com.project.dto.response.BasicResponse;
import com.project.entity.Equipments;
import com.project.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class EquipmentsManagerController {

    private final EquipmentService equipmentService;
    @GetMapping(GET_ALL_EQUIPMENTS_OF_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Equipments>>> findAllMyEquipments(@RequestParam String token) {

        return ResponseEntity.ok(BasicResponse.<List<Equipments>>builder()
                .status(200)
                .message("Equipments of an Employee are successfully found for manager.")
                .data(equipmentService.findAllEquipmentsOfEmployee(token))
                .build());
    }
}
