package com.project.service;


import com.project.dto.request.CompanyTokenRequestDto;
import com.project.dto.request.CompanyCreateRequestDto;
import com.project.dto.request.CompanyUpdateRequestDto;
import com.project.dto.response.CompanyGetAllResponseDto;
import com.project.dto.response.CompanyManagerResponseDto;
import com.project.dto.response.ManagerResponseDto;
import com.project.entity.Company;
import com.project.exception.CompanyServiceException;
import com.project.exception.ErrorType;
import com.project.manager.ManagerManager;
import com.project.mapper.CompanyMapper;
import com.project.rabbitmq.model.ApproveManagerModel;
import com.project.rabbitmq.model.RejectManagerModel;
import com.project.rabbitmq.producer.ApproveManagerProducer;
import com.project.rabbitmq.producer.RejectManagerProducer;
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
    private final ApproveManagerProducer approveManagerProducer;
    private final RejectManagerProducer rejectManagerProducer;

    private final ManagerManager managerManager;




    public void createCompany(CompanyCreateRequestDto dto) {
        companyRepository.save(CompanyMapper.INSTANCE.fromCompanyCreateRequestDtoToCompany(dto));
    }

    public Boolean updateCompany(CompanyUpdateRequestDto dto) {
        System.out.println("Update mi sorun");
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        Company company = companyRepository.findById(dto.getId()).orElseThrow(()->new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
        System.out.println("2. update");
            company.setManagerId(manager.getId());
            company.setName(manager.getName());
            company.setTitle(dto.getTitle());
            company.setDescription(dto.getDescription());
            company.setAddress(dto.getAddress());
            company.setPhone(dto.getPhone());
            company.setEmail(manager.getEmail());
            company.setWebsite(dto.getWebsite());
            company.setLogo(dto.getLogo());
            company.setSector(dto.getSector());
            company.setTaxNumber(manager.getTaxNumber());
            company.setTaxOffice(dto.getTaxOffice());
            company.setMersisNo(dto.getMersisNo());
            company.setVision(dto.getVision());
            company.setMission(dto.getMission());
            company.setCountry(dto.getCountry());
            company.setCity(dto.getCity());
            company.setEmployeeCount(dto.getEmployeeCount());
            company.setFounded(dto.getFounded());
            company.setFoundingYear(dto.getFoundingYear());
            company.setLinkedin(dto.getLinkedin());
            company.setMembershipPlan(dto.getMembershipPlan());
            company.setUpdateAt(System.currentTimeMillis());
            company.setStatus(EStatus.ACTIVE);
            companyRepository.save(company);
            return true;

    }


    public List<CompanyGetAllResponseDto> getAll(String token) {
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        return companyRepository.findAll()
                .stream()
                .filter(company -> company.getStatus().equals(EStatus.ACTIVE))
                .map(companyMapper::fromCompanyToCompanyGetAllResponseDto)
                .collect(Collectors.toList());
    }


    //statusu pending olan şirketleri listelemeye yarar
    public List<CompanyManagerResponseDto> getAllPendingCompanies(String token) {
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));

       return companyRepository.findAll().stream().
                filter(company -> company.getStatus().equals(EStatus.PENDING))
                .map(companyMapper::fromCompanyToCompanyManagerResponseDto)
               .collect(Collectors.toList());
    }

    public Boolean approveCompany(CompanyTokenRequestDto dto) {
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        Company company = companyRepository.findById(dto.getId()).orElseThrow(()->new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));

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

    public Boolean rejectCompany(CompanyTokenRequestDto dto) {
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        Company company = companyRepository.findById(dto.getId()).orElseThrow(()->new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
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
}













