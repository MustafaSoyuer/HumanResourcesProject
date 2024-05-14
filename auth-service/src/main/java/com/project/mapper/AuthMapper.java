package com.project.mapper;

import com.project.dto.request.RegisterEmployeeRequestDto;
import com.project.dto.request.RegisterManagerRequestDto;
import com.project.dto.response.AuthResponseDto;
import com.project.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromRegisterManagerRequestDtoToAuth(final RegisterManagerRequestDto dto);

    Auth fromRegisterEmployeeRequestDtoToAuth(final RegisterEmployeeRequestDto dto);


    AuthResponseDto fromAuthToAuthResponseDto(final Auth auth);
}