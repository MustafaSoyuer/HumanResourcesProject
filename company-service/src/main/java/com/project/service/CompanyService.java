package com.project.service;

import com.project.dto.request.CompanyCreateRequestDto;
import com.project.dto.request.CompanyUpdateRequestDto;
import com.project.dto.response.CompanyGetAllResponseDto;
import com.project.dto.response.CompanyManagerResponseDto;
import com.project.entity.Company;
import com.project.exception.CompanyServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.CompanyMapper;
import com.project.repository.CompanyRepository;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;


    public void createCompany(CompanyCreateRequestDto dto) {
        companyRepository.save(CompanyMapper.INSTANCE.fromCompanyCreateRequestDtoToCompany(dto));
    }

    public void updateCompany(CompanyUpdateRequestDto dto) {
        //TODO: token eklemesi yapılacak.
        Optional<Company> company = companyRepository.findById(dto.getId());
        if (company.isEmpty()) {
            throw new CompanyServiceException(ErrorType.ERROR_INVALID_COMPANY_ID);
        }
        companyRepository.save(CompanyMapper.INSTANCE.fromCompanyUpdateRequestDtoToCompany(dto));
    }


    public List<CompanyGetAllResponseDto> getAll() {
        //TODO: token eklemesi yapılacak.
       return companyRepository.findAll()
               .stream()
               .map(companyMapper::fromCompanyToCompanyGetAllResponseDto)
               .collect(Collectors.toList());
    }


    //statusu pending olan şirketleri listelemeye yarar
    public List<CompanyManagerResponseDto> getAllPendingCompanies() {
       return companyRepository.findAll().stream().
                filter(company -> company.getStatus().equals(EStatus.PENDING))
                .map(companyMapper::fromCompanyToCompanyManagerResponseDto).collect(Collectors.toList());
    }
}
