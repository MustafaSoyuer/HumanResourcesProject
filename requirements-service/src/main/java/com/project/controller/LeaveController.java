package com.project.controller;
import com.project.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(LEAVE)
public class LeaveController {
    private final LeaveService leaveService;
}
