package com.project.controller;

import com.project.dto.request.SaveManagerRequestDto;
import com.project.entity.Manager;
import com.project.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.constants.RestApiUrls.*;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping(SAVE_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<Manager> save(@RequestBody SaveManagerRequestDto dto) {
        return ResponseEntity.ok(managerService.save(dto));
    }

}
