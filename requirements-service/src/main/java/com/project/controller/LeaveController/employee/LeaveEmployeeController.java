package com.project.controller.LeaveController.employee;

import com.project.dto.request.RequestLeaveDto;
import com.project.dto.response.BasicResponse;
import com.project.entity.Leave;
import com.project.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(EMPLOYEE)
@RequiredArgsConstructor
public class LeaveEmployeeController {
    private final LeaveService leaveService;

    /**
     * Employee kendi izinlerini buradan bulabilir.
     * @param dto
     * @return
     */

    @GetMapping(FIND_ALL_MY_LEAVES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Leave>>> findAllMyLeaves(@RequestParam String token) {
        return ResponseEntity.ok(BasicResponse.<List<Leave>>builder()
                .status(200)
                .message("Leaves are successfully found.")
                .data(leaveService.findAllMyLeavesForEmployee(token))
                .build());
    }

    @PutMapping(REQUEST_LEAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> requestLeave(@RequestBody RequestLeaveDto dto) {
        leaveService.requestLeaveFromEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Leave requested by employee successfully.")
                .data(true)
                .build());
    }
}
