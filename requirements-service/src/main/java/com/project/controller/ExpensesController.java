package com.project.controller;
import com.project.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EXPENSES)
public class ExpensesController {
    private final ExpensesService expensesService;
}
