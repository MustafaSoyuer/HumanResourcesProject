package com.project.dto.request;



import com.project.utility.enums.EShiftType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddShiftRequestDto {
    private String token;
    private Long employeeId;
    private String employeeName;
    private String employeeSurname;
    private EShiftType shiftType;
    private LocalDate startTime;
    private LocalDate endTime;
}
