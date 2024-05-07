package com.project.mapper;

import com.project.dto.response.BaseLeaveResponseDto;
import com.project.entity.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LeaveMapper {

    LeaveMapper INSTANCE = Mappers.getMapper(LeaveMapper.class);
    BaseLeaveResponseDto toResponseDto(final Leave leave);
}
