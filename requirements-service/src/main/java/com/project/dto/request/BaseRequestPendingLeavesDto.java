package com.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequestPendingLeavesDto {
    private String token;
    private Long managerId; //TODO: bunun yerine sadece token da istemiş mesela buseler öyle mi yapsak? bu kısmı silelim mi
}
