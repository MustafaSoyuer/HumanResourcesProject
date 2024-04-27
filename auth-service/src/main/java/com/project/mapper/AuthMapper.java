package com.project.mapper;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    AuthLoginResponseDto fromAuthLoginRequestDtoToAuthLoginResponseDto (final AuthLoginRequestDto auth);
}