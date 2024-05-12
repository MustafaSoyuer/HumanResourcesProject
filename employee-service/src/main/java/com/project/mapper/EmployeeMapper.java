package com.project.mapper;

import com.project.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.project.dto.request.SaveEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.dto.response.EmployeeResponseDto;
import com.project.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee fromUpdateEmployeeRequestDtoToEmployee(final UpdateEmployeeRequestDto dto);

    Employee fromManagerOrAdminUpdateEmployeeRequestDtoToEmployee(final ManagerOrAdminUpdateEmployeeRequestDto dto);

    Employee fromSaveEmployeeRequestDtoToEmployee(final SaveEmployeeRequestDto dto);

    EmployeeResponseDto fromEmployeeToEmployeeResponseDto(final Employee employee);

}
