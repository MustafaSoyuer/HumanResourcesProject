package com.project.controller.ShiftController.employee;

import com.project.dto.response.BasicResponse;
import com.project.entity.Shift;
import com.project.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.utility.constants.RestApiUrls.EMPLOYEE;
import static com.project.utility.constants.RestApiUrls.GET_ALL_MY_SHIFTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
public class ShiftEmployeeController {
    private final ShiftService shiftService;

    @GetMapping(GET_ALL_MY_SHIFTS)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Shift>>> getAllMyShifts(@RequestParam String token) {
        return ResponseEntity.ok(BasicResponse.<List<Shift>>builder()
                .status(200)
                .message("Shifts are found.")
                .data(shiftService.getAllMyShifts(token))
                .build());

    }

}
