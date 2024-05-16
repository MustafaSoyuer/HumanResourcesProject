package com.project.manager;

import com.project.dto.response.ManagerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9095/dev/v1/manager", name = "requirements-manager")
public interface ManagerManager {
    @GetMapping("/find-by-token")
    @CrossOrigin("*")
    ResponseEntity<ManagerResponseDto> findByToken(@RequestParam("token") String token);
}
