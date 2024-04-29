package com.project.service;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.dto.request.RegisterAdminRequestDto;
import com.project.dto.request.RegisterEmployeeRequestDto;
import com.project.dto.request.RegisterManagerRequestDto;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.dto.response.RegisterEmployeeResponseDto;
import com.project.dto.response.RegisterManagerResponseDto;
import com.project.entity.Auth;
import com.project.exception.AuthServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.AuthMapper;
import com.project.rabbitmq.model.CreateUserModel;
import com.project.rabbitmq.producer.CreateUserProducer;
import com.project.repository.AuthRepository;
import com.project.utility.JwtTokenManager;
import com.project.utility.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateUserProducer createUserProducer;


    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        System.out.println("token....: ahanada buradaaaaaaaaa ->>>>>> "+token);
        if (token.isEmpty())
            throw new AuthServiceException(ErrorType.ERROR_CREATE_TOKEN);
        return AuthLoginResponseDto.builder().token(token.get()).role(auth.get().getRole().name()).build();
    }

    /**
     * Süper admin token ile kontrol edilir.
     * @param dto
     * @return
     */
    public RegisterManagerResponseDto registerManager(RegisterManagerRequestDto dto) {
        Optional<Long> authId= jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }
        Optional<Auth> admin = authRepository.findOptionalById(authId.get());
        if (admin.isEmpty()){
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }

        Auth auth = AuthMapper.INSTANCE.fromRegisterManagerRequestToAuth(dto);
        auth.setRole(ERole.MANAGER);
        authRepository.save(auth);
        createUserProducer.sendMessage(CreateUserModel.builder()
                        .authId(auth.getId())
                        .address(dto.getAddress())
                        .company(dto.getCompany())
                        .email(dto.getEmail())
                        .name(dto.getName())
                        .phone(dto.getPhone())
                        .surname(dto.getSurname())
                        .taxNo(dto.getTaxNo())
                .build());
        return AuthMapper.INSTANCE.fromAuthToRegisterManagerResponseDto(auth);
    }

    public RegisterEmployeeResponseDto registerEmployee(RegisterEmployeeRequestDto dto) {
        return null;
    }

    public boolean isCompanyEmail(String email, String company) {
        //TODO: kontrol edilecek. email doğrulaması yapmayı amaçladım. Şirket maili ile girmeli. Ayrıca bir domain istenebilir?

        if (email.equalsIgnoreCase(company))
            return true;
        else {
            throw new AuthServiceException(ErrorType.ERROR_EMAIL_ISCOMPANY);
        }
    }

    public Void registerAdmin(RegisterAdminRequestDto dto) {

        Auth auth = Auth.builder().email(dto.getEmail()).password(dto.getPassword()).build();
        auth.setRole(ERole.ADMIN);
        authRepository.save(auth);
        return null;
    }
}
