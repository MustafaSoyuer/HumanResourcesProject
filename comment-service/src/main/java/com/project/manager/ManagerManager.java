package com.project.manager;

import com.project.dto.response.ManagerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9095/dev/v1/manager", name = "comment-manager")
public class ManagerManager {

}
