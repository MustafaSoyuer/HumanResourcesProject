package com.project.controller.ShiftController.manager;

import com.project.dto.request.AddShiftRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.entity.Shift;
import com.project.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER)
public class ShitfManagerController {
    private final ShiftService shiftService;

    /**
     * Manager Employee ye shift ekleyebilir
     * @param dto
     * @return
     */
    @PostMapping(ADD_SHIFT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> addShift(@RequestBody AddShiftRequestDto dto) {
        shiftService.addShift(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Shift added successfully.")
                .data(true)
                .build());
    }

    @GetMapping(GET_SHIFT_OF_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Shift>>> getShiftOfEmployee(@RequestParam String token, @RequestParam Long employeeId) {
        return ResponseEntity.ok(BasicResponse.<List<Shift>>builder()
                .status(200)
                .message("All Shifts are found.")
                .data(shiftService.getShiftOfEmployee(token, employeeId))
                .build());
    }

    @GetMapping(GET_ALL_SHIFTS_OF_EMPLOYEES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Shift>>> getAllShiftsOfEmployees(@RequestParam String token) {
        return ResponseEntity.ok(BasicResponse.<List<Shift>>builder()
                .status(200)
                .message("All Shifts are found.")
                .data(shiftService.getAllShiftsOfEmployees(token))
                .build());

    }



}
