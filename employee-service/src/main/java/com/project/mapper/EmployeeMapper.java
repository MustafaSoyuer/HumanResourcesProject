package com.project.mapper;

import com.project.dto.request.ManagerUpdateEmployeeRequestDto;
import com.project.dto.request.SaveEmployeeRequestDto;
import com.project.dto.request.UpdateEmployeeRequestDto;
import com.project.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee fromSaveEmployeeRequestDtoToEmployee(final SaveEmployeeRequestDto dto);

    Employee fromUpdateEmployeeRequestDtoToEmployee(final UpdateEmployeeRequestDto dto);

    Employee fromManagerUpdateEmployeeRequestDtoToEmployee(final ManagerUpdateEmployeeRequestDto dto);

}
