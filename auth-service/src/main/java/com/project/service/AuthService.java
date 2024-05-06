package com.project.service;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.dto.request.RegisterAdminRequestDto;
import com.project.dto.request.RegisterManagerRequestDto;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.dto.response.RegisterManagerResponseDto;
import com.project.entity.Auth;
import com.project.exception.AuthServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.AuthMapper;
import com.project.rabbitmq.model.CreateManagerModel;
import com.project.rabbitmq.producer.CreateManagerProducer;
import com.project.repository.AuthRepository;
import com.project.utility.CodeGenerator;
import com.project.utility.JwtTokenManager;
import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateManagerProducer createManagerProducer;


    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);

        if (auth.get().getStatus().equals(EStatus.ACTIVE)) {
            Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
            if(token.isEmpty()){
                throw new AuthServiceException(ErrorType.INVALID_TOKEN);
            }else {
                return AuthLoginResponseDto.builder().token(token.get()).role(auth.get().getRole()).build();
            }
        }else {
            throw new AuthServiceException(ErrorType.USER_IS_NOT_ACTIVE);

        }
    }

    /**
     * Süper admin token ile kontrol edilir.
     * @param dto
     * @return
     */
    public RegisterManagerResponseDto registerManager(RegisterManagerRequestDto dto) {

        Auth auth = AuthMapper.INSTANCE.fromRegisterManagerRequestToAuth(dto);
        auth.setRole(ERole.MANAGER);
        auth.setPassword(dto.getName() + CodeGenerator.generateCode());
        auth.setCreateAt(System.currentTimeMillis());
        authRepository.save(auth);
        createManagerProducer.sendMessage(CreateManagerModel.builder()  //
                .authId(auth.getId())
                .address(dto.getAddress())
                .company(dto.getCompany())
                .email(dto.getEmail())
                .name(dto.getName())
                .phone(dto.getPhone())
                .surname(dto.getSurname())
                .taxNumber(dto.getTaxNumber())
                .password(auth.getPassword()) //todo: burası düzeltilmeli.
                .createAt(System.currentTimeMillis())
                .status(EStatus.PENDING)
                .build());
        return AuthMapper.INSTANCE.fromAuthToRegisterManagerResponseDto(auth);
    }


    public boolean isCompanyEmail(String email, String company) {
        //TODO: kontrol edilecek. email doğrulaması yapmayı amaçladım. Şirket maili ile girmeli. Ayrıca bir domain istenebilir?

        if (email.equalsIgnoreCase(company))
            return true;
        else {
            throw new AuthServiceException(ErrorType.ERROR_EMAIL_ISCOMPANY);
        }
    }

    public Boolean registerAdmin(RegisterAdminRequestDto dto) {
        Optional<Long> authId= jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }
        Optional<Auth> admin = authRepository.findOptionalById(authId.get());
        if (admin.isEmpty()){
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }


        Auth auth = Auth.builder().email(dto.getEmail()).password(dto.getPassword()).build();
        auth.setRole(ERole.ADMIN);
        auth.setStatus(EStatus.ACTIVE);
        authRepository.save(auth);
        return true;
    }

    public Boolean approveAuth(Long authId) {
        Optional<Auth> auth = authRepository.findOptionalById(authId);
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        auth.get().setStatus(EStatus.ACTIVE);
        auth.get().setUpdateAt(System.currentTimeMillis());
        authRepository.save(auth.get());
        //authtan mailsedere haber gidicek.
        return true;
    }

    public Boolean rejectAuth(Long authId) {
        Optional<Auth> auth = authRepository.findOptionalById(authId);
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        auth.get().setStatus(EStatus.PASSIVE);
        auth.get().setUpdateAt(System.currentTimeMillis());
        authRepository.save(auth.get());
        //authtan mailsedere haber gidicek.
        return true;
    }




}