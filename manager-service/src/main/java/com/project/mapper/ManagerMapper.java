package com.project.mapper;

import com.project.dto.request.AdminUpdateManagerRequestDto;
import com.project.dto.request.SaveManagerRequestDto;
import com.project.dto.request.UpdateManagerRequestDto;
import com.project.dto.response.ManagerCompanyResponseDto;
import com.project.dto.response.SaveManagerResponseDto;
import com.project.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagerMapper {

    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);


    Manager fromSaveManagerRequestDtoToManager(final SaveManagerRequestDto dto);

    SaveManagerResponseDto fromManagerToSaveManagerResponseDto(final Manager manager);

    Manager fromUpdateManagerRequestDtoToManager(final UpdateManagerRequestDto dto);

    Manager fromAdminUpdateManagerRequestDtoToManager(final AdminUpdateManagerRequestDto dto);

    ManagerCompanyResponseDto fromManagerToManagerCompanyResponseDto(final Manager manager);
}
