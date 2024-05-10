package com.project.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import static com.project.utility.constants.RestApiUrls.FIND_EMPLOYEE_BY_TOKEN;

@FeignClient(url = "http://localhost:9094/dev/v1/employee",name = "requirements-employee")
public class EmployeeManager {

}
