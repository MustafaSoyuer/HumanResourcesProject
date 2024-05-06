package com.project.mapper;

import com.project.dto.request.CompanyCreateRequestDto;
import com.project.dto.request.CompanyUpdateRequestDto;
import com.project.dto.response.CompanyGetAllResponseDto;
import com.project.dto.response.CompanyManagerResponseDto;
import com.project.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    CompanyMapper INSTANCE= Mappers.getMapper(CompanyMapper.class);

    Company fromCompanyCreateRequestDtoToCompany(CompanyCreateRequestDto dto);
    Company fromCompanyUpdateRequestDtoToCompany (final CompanyUpdateRequestDto dto);


    CompanyGetAllResponseDto fromCompanyToCompanyGetAllResponseDto(final Company company);

    CompanyManagerResponseDto fromCompanyToCompanyManagerResponseDto(final Company company);

    CompanyUpdateRequestDto fromCompanyToCompanyUpdateRequestDto(final Company company);
}
