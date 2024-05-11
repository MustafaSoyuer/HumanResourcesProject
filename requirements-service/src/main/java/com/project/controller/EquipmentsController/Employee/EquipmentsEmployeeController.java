package com.project.controller.EquipmentsController.Employee;

import com.project.dto.request.BaseRequestForRequirementsDto;
import com.project.dto.request.EquipmentRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.entity.Equipments;
import com.project.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(EMPLOYEE)
@RequiredArgsConstructor
public class EquipmentsEmployeeController {

    private final EquipmentService equipmentService;

    @GetMapping(GET_ALL_EQUIPMENT_OF_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Equipments>>> findAllMyEquipments(@RequestParam String token) {

        return ResponseEntity.ok(BasicResponse.<List<Equipments>>builder()
                .status(200)
                .message("Equipments of an Employee are successfully found.")
                .data(equipmentService.findAllMyEquipmentsForEmployee(token))
                .build());
    }

    @PutMapping(REQUEST_EQUIPMENT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> requestLeave(@RequestBody EquipmentRequestDto dto) {
        equipmentService.requestEquipmentFromEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Equipment requested by employee successfully.")
                .data(true)
                .build());
    }

    @PutMapping(RETURN_EQUIPMENT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> returnEquipment(@RequestBody BaseRequestForRequirementsDto dto) {
        equipmentService.returnEquipmentFromEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Equipment returned by employee successfully.")
                .data(true)
                .build());
    }



}
