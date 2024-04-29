package com.project.service;

import com.project.dto.request.AuthLoginRequestDto;
import com.project.dto.request.RegisterEmployeeRequestDto;
import com.project.dto.request.RegisterManagerRequestDto;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.dto.response.RegisterEmployeeResponseDto;
import com.project.dto.response.RegisterManagerResponseDto;
import com.project.entity.Auth;
import com.project.exception.AuthServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.AuthMapper;
import com.project.repository.AuthRepository;
import com.project.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;


    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        System.out.println(token);
        if (token.isEmpty())
            throw new AuthServiceException(ErrorType.ERROR_CREATE_TOKEN);
        return AuthLoginResponseDto.builder().token(token.get()).role(auth.get().getRole().name()).build();
    }

    public RegisterManagerResponseDto registerManager(RegisterManagerRequestDto dto) {
        //if (isCompanyEmail(dto.getEmail(), dto.getCompany())){
            Auth auth = AuthMapper.INSTANCE.fromRegisterManagerRequestToAuth(dto);
            //TODO: burada manager kaydı yapılırken, adminin onaylaması gerekmektedir.
            // if(admin.isApproved()){ gibi -> setState.aktif
            authRepository.save(auth);
            return AuthMapper.INSTANCE.fromAuthToRegisterManagerResponseDto(auth);
//        }else {
//            throw new AuthServiceException(ErrorType.ERROR_EMAIL_ISCOMPANY);
//        }



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
}
