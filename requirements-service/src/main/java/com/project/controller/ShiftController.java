package com.project.controller;
import com.project.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(SHIFT)
public class ShiftController {
    private final ShiftService shiftService;
}
