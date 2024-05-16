package com.project.service;


import ch.qos.logback.classic.LoggerContext;
import com.project.dto.request.CompanyTokenRequestDto;
import com.project.dto.request.CompanyCreateRequestDto;
import com.project.dto.request.CompanyUpdateRequestDto;
import com.project.dto.response.AuthResponseDto;
import com.project.dto.response.CompanyGetAllResponseDto;
import com.project.dto.response.CompanyManagerResponseDto;
import com.project.dto.response.ManagerResponseDto;
import com.project.entity.Company;
import com.project.exception.CompanyServiceException;
import com.project.exception.ErrorType;
import com.project.manager.AuthManager;
import com.project.manager.ManagerManager;
import com.project.mapper.CompanyMapper;
import com.project.rabbitmq.model.ApproveManagerModel;
import com.project.rabbitmq.model.RejectManagerModel;
import com.project.rabbitmq.producer.ApproveManagerProducer;
import com.project.rabbitmq.producer.RejectManagerProducer;
import com.project.repository.CompanyRepository;
import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ApproveManagerProducer approveManagerProducer;
    private final RejectManagerProducer rejectManagerProducer;
    private final AuthManager authManager;
    private final ManagerManager managerManager;



    public void createCompany(CompanyCreateRequestDto dto) {
        companyRepository.save(CompanyMapper.INSTANCE.fromCompanyCreateRequestDtoToCompany(dto));
    }

    public Boolean updateCompany(CompanyUpdateRequestDto dto) {
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));

            Company company = companyRepository.findById(dto.getId()).orElseThrow(() -> new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
            company.setId(company.getId());
            company.setManagerId(dto.getManagerId());
            company.setName(dto.getName());
            company.setTitle(dto.getTitle());
            company.setAddress(dto.getAddress());
            company.setPhone(dto.getPhone());
            company.setEmail(dto.getEmail());
            company.setWebsite(dto.getWebsite());
            company.setSector(dto.getSector());
            company.setTaxNumber(dto.getTaxNumber());
            company.setEmployeeCount(dto.getEmployeeCount());
            company.setUpdateAt(System.currentTimeMillis());
            company.setStatus(EStatus.ACTIVE);
            companyRepository.save(company);
            return true;

    }


    public List<CompanyGetAllResponseDto> getAll(String token) {
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(token).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        if (auth.getRole().equals(ERole.ADMIN)) {
            return companyRepository.findAll()
                    .stream()
                    .filter(company -> company.getStatus().equals(EStatus.ACTIVE))
                    .map(companyMapper::fromCompanyToCompanyGetAllResponseDto)
                    .collect(Collectors.toList());
        }
        throw new CompanyServiceException(ErrorType.UNAUTHORIZED);
    }


    //statusu pending olan şirketleri listelemeye yarar
    public List<CompanyManagerResponseDto> getAllPendingCompanies(String token) {
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(token).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        if (auth.getRole().equals(ERole.ADMIN)) {
            return companyRepository.findAll().stream().
                      filter(company -> company.getStatus().equals(EStatus.PENDING))
                      .map(companyMapper::fromCompanyToCompanyManagerResponseDto)
                        .collect(Collectors.toList()); }
        throw new CompanyServiceException(ErrorType.UNAUTHORIZED);
    }

    public Boolean approveCompany(CompanyTokenRequestDto dto) {
        System.out.println("Company başlangıcı approve");
        log.info("Approving company with token: {}"+ dto.getToken());
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        Company company = companyRepository.findById(dto.getId()).orElseThrow(()->new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
        if (auth.getRole().equals(ERole.ADMIN)) {
            company.setStatus(EStatus.ACTIVE);
            company.setUpdateAt(System.currentTimeMillis());
            companyRepository.save(company);
            approveManagerProducer.sendMessage(ApproveManagerModel.builder()
                    .id(company.getId())
                    .managerId(company.getManagerId())
                    .updateAt(System.currentTimeMillis())
                    .status(company.getStatus())
                    .build());
            return true;
        }
        throw new CompanyServiceException(ErrorType.UNAUTHORIZED);
    }

    public Boolean rejectCompany(CompanyTokenRequestDto dto) {
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));

        Company company = companyRepository.findById(dto.getId()).orElseThrow(()->new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
        if (auth.getRole().equals(ERole.ADMIN)) {
            company.setStatus(EStatus.PASSIVE);
            company.setUpdateAt(System.currentTimeMillis());
            companyRepository.save(company);
            rejectManagerProducer.sendMessage(RejectManagerModel.builder()
                    .id(company.getId())
                    .managerId(company.getManagerId())
                    .updateAt(System.currentTimeMillis())
                    .status(company.getStatus())
                    .build());
            return true;
        }
        throw new CompanyServiceException(ErrorType.UNAUTHORIZED);
    }
}













