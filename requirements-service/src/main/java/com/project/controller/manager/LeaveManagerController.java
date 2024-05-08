package com.project.controller.manager;

import com.project.dto.request.*;
import com.project.dto.response.BaseLeaveResponseDto;
import com.project.dto.response.BasicResponse;
import com.project.entity.Leave;
import com.project.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class LeaveManagerController {
    private final LeaveService leaveService;

    /**
     * Manager, employee için izin ekleyebilecek.
     * @param dto
     * @return
     */
    @PostMapping(ADD_LEAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> addLeaveToEmployee(@RequestBody AddLeaveRequestDto dto){
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                        .status(200)
                        .message("Leave added")
                        .data(leaveService.addLeaveToEmployee(dto))
                .build());
    }

    /**
     * Manager, çalışanın aldığı izini onaylayabilecek
     * @param dto
     * @return
     */
    @PostMapping(APPROVE_LEAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> approveLeave(@RequestBody BaseRequestForRequirementsDto dto){
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Leave approved.")
                .data(leaveService.approveLeaveForEmployee(dto))
                .build());
    }

    /**
     * Manager, employee için izinleri reddedebilecek.
     * @param dto
     * @return
     */
    @PostMapping(REJECT_LEAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> rejectLeaveOfEmployee(@RequestBody RejectLeaveRequestDto dto){
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Leave rejected")
                .data(leaveService.rejectLeaveForEmployee(dto))
                .build());
    }

    /**
     * Manager, bir çalışana ait tüm izinleri görebilecek.
     * @param dto
     * @return
     */
    @GetMapping(GET_ALL_LEAVES_OF_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<BaseLeaveResponseDto>>> getAllLeavesOfEmployee(@RequestBody BaseRequestForEmployeeDto dto){
        return ResponseEntity.ok(BasicResponse.<List<BaseLeaveResponseDto>>builder()
                .status(200)
                .message("Leaves for an employee are found.")
                .data(leaveService.findAllLeavesOfAnEmployee(dto))
                .build());
    }

    /**
     * Manager, çalışanlarına ait tüm bekleyen izinleri görebilecek
     * @param dto
     * @return
     */
    @GetMapping(GET_ALL_PENDING_LEAVES_OF_EMPLOYEES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<BaseLeaveResponseDto>>> getAllPendingLeavesOfEmployees(@RequestBody BaseRequestPendingLeavesDto dto){
        return ResponseEntity.ok(BasicResponse.<List<BaseLeaveResponseDto>>builder()
                .status(200)
                .message("Pending leaves for manager are found.")
                .data(leaveService.findAllPendingLeavesOfEmployees(dto))
                .build());
    }





}
