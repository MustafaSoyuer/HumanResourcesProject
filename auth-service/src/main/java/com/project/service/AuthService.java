package com.project.service;

import com.project.dto.request.*;
import com.project.dto.response.AuthLoginResponseDto;
import com.project.dto.response.RegisterManagerResponseDto;
import com.project.entity.Auth;
import com.project.exception.AuthServiceException;
import com.project.exception.ErrorType;
import com.project.mapper.AuthMapper;
import com.project.rabbitmq.model.CreateManagerModel;
import com.project.rabbitmq.model.SendMailActivationModel;
import com.project.rabbitmq.model.SendMailRejectModel;
import com.project.rabbitmq.producer.CreateManagerProducer;
import com.project.rabbitmq.producer.SendMailActivationProducer;
import com.project.rabbitmq.producer.SendMailRejectProducer;
import com.project.repository.AuthRepository;
import com.project.utility.CodeGenerator;
import com.project.utility.JwtTokenManager;
import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateManagerProducer createManagerProducer;
    private final SendMailActivationProducer sendMailActivationProducer;
    private final SendMailRejectProducer sendMailRejectProducer;

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
    public Boolean registerManager(RegisterManagerRequestDto dto) {
        Optional<Auth> OptionalAuth = authRepository.findOptionalByEmail(dto.getEmail());
        if (OptionalAuth.isPresent())
            throw new AuthServiceException(ErrorType.USER_ALREADY_EXISTS);

        Auth auth = AuthMapper.INSTANCE.fromRegisterManagerRequestToAuth(dto);
        auth.setRole(ERole.MANAGER);
        auth.setPassword(dto.getName() + CodeGenerator.generateCode() + ".");
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
                .createAt(System.currentTimeMillis())
                .status(EStatus.PENDING)
                .build());
        return true;
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
        Optional<Auth> optionalAuth = authRepository.findOptionalById(authId);
        if (optionalAuth.isEmpty())
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        Auth auth=optionalAuth.get();
        auth.setStatus(EStatus.ACTIVE);
        auth.setUpdateAt(System.currentTimeMillis());
        authRepository.save(auth);
        sendMailActivationProducer.sendMessage(SendMailActivationModel.builder()
                        .id(auth.getId())
                        .email(auth.getEmail())
                        .password(auth.getPassword())
                        .updateAt(System.currentTimeMillis())
                .build());
        return true;
    }

    public Boolean rejectAuth(Long authId) {
        Optional<Auth> auth = authRepository.findOptionalById(authId);
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        auth.get().setStatus(EStatus.PASSIVE);
        auth.get().setUpdateAt(System.currentTimeMillis());
        authRepository.save(auth.get());
        sendMailRejectProducer.sendMessage(SendMailRejectModel.builder()
                        .id(auth.get().getId())
                        .email(auth.get().getEmail())
                        .updateAt(System.currentTimeMillis())
                .build());
        return true;
    }


    public Boolean changePassword(ChangePasswordDto dto) {

        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getOldPassword());
        if (auth.isEmpty()){
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }
        if (!auth.get().getPassword().equalsIgnoreCase(dto.getOldPassword())) {
            throw new AuthServiceException(ErrorType.PASSWORD_NOT_MATCH);
        }
        if (!dto.getNewPassword().equalsIgnoreCase(dto.getConfirmPassword())) {
            throw new AuthServiceException(ErrorType.PASSWORD_NOT_MATCH);
        }
        auth.get().setPassword(dto.getNewPassword());
        authRepository.save(auth.get());
        return true;
    }
    public void addEmployee(AddEmployeeRequestDto dto) {
        Optional<Auth> OptionalAuth = authRepository.findOptionalByEmail(dto.getEmail());
        if (OptionalAuth.isPresent()) {
            throw new AuthServiceException(ErrorType.USER_ALREADY_EXISTS);
        }

        Auth auth = AuthMapper.INSTANCE.fromAddEmployeeRequestDtoToAuth(dto);

        String name = normalizeAndRemoveSpaces(dto.getName().toLowerCase());
        String surname = normalizeAndRemoveSpaces(dto.getSurname().toLowerCase());
        String password = name + surname+".";
        auth.setPassword(password);

        auth.setStatus(EStatus.ACTIVE);
        auth.setCreateAt(System.currentTimeMillis());
        auth.setUpdateAt(System.currentTimeMillis());
        auth.setRole(ERole.EMPLOYEE);
        authRepository.save(auth);



    }

    public String normalizeAndRemoveSpaces(String input) {
        String normalizedString = Normalizer.normalize(input,Normalizer.Form.NFD);
        // Add the following lines to preserve the following characters
        normalizedString = normalizedString.replace("ı","i");
        normalizedString = normalizedString.replace("ö","o");
        normalizedString = normalizedString.replace("ü","u");
        normalizedString = normalizedString.replace("ç","c");
        normalizedString = normalizedString.replace("ş","s");
        normalizedString = normalizedString.replace("ğ","g");

        normalizedString = normalizedString.replaceAll("[^\\p{ASCII}]","");
        normalizedString = normalizedString.replaceAll("\\s+","");

        return normalizedString;
    }

}