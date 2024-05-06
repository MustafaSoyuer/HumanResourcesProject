package com.project.controller;

import com.project.dto.request.ActivateCompanyStatusRequestDto;
import com.project.dto.request.CompanyCreateRequestDto;
import com.project.dto.request.CompanyUpdateRequestDto;
import com.project.dto.response.BasicResponse;
import com.project.dto.response.CompanyGetAllResponseDto;
import com.project.dto.response.CompanyManagerResponseDto;
import com.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
public class CompanyController {

    private final CompanyService companyService;


    /**
     * Yeni bir şirket oluşturmayı sağlayan methodtur.
     * @param dto
     * @return
     */
    @PostMapping(CREATE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> createCompany(@RequestBody CompanyCreateRequestDto dto) {
        companyService.createCompany(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Company created successfully")
                .data(true)
                .build()
        );
    }

    /**
     * Bu method şirketin ID'si ile doğrulama yaparak bilgilerini güncellemeyi sağlar.
     * @param dto -> CompanyUpdateRequestDto
     * @return -> true, false
     */
    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateCompany(@RequestBody CompanyUpdateRequestDto dto) {
        companyService.updateCompany(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Company updated successfully")
                .data(true)
                .build()
        );
    }

    /**
     * Bu method ile şirketlerin listesi getirilir.
     * @return -> List<CompanyGetAllResponseDto>
     */
    @GetMapping(GET_ALL)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<CompanyGetAllResponseDto>>> getAll() {
        return ResponseEntity.ok(BasicResponse.<List<CompanyGetAllResponseDto>>builder()
                .status(200)
                .message("Companies get all successfully")
                .data(companyService.getAll())
                .build());
    }


    /**
     * Bu method sitede bulunan toplam şirket sayısını verir.
     * @return -> Long(şirket sayısı)
     */
    @GetMapping(GET_ALL_COMPANY_COUNT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Long>> getAllCompanyCount() {
        return ResponseEntity.ok(BasicResponse.<Long>builder()
                .status(200)
                .message("Company count get all successfully")
                .data(companyService.getAll().stream().count())
                .build()
        );
    }

    /**
     * Site yöneticisinin başvuruda bulunmuş şirketleri görüntülemesini sağlar.
     * @return
     */

    @GetMapping(GET_ALL_PENDING_COMPANIES)
    @CrossOrigin("*")
   // @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<BasicResponse<List<CompanyManagerResponseDto>>> getAllPendingCompanies() {
        return ResponseEntity.ok(BasicResponse.<List<CompanyManagerResponseDto>>builder()
                .status(200)
                .message("Pending Companies received all successfully")
                .data(companyService.getAllPendingCompanies())
                .build());
    }

    /**
     * Site yöneticisinin başvuruda bulunan şirketleri onaylamasını sağlayan methodtur.
     * @param
     * @return
     */
    @PostMapping(APPROVE_COMPANY + "/{id}")
    @CrossOrigin("*")
   // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BasicResponse<Boolean>> approveCompany(@PathVariable String id) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                        .status(200)
                        .message("Company approved successfully")
                        .data(companyService.approveCompany(id))
                .build());
    }

    @PostMapping(REJECT_COMPANY + "/{id}")
    @CrossOrigin("*")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BasicResponse<Boolean>> rejectCompany(@PathVariable String id) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Company rejected.")
                .data(companyService.rejectCompany(id))
                .build());
    }




}
