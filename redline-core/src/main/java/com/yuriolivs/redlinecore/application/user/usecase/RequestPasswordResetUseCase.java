package com.yuriolivs.redlinecore.application.user.usecase;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.CacheServiceInterface;
import com.yuriolivs.redlinecore.domain.service.CodeGeneratorInterface;
import com.yuriolivs.redlinecore.domain.service.EmailSenderInterface;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RequestPasswordResetUseCase {
    private final UserRepositoryInterface userRepository;
    private final EmailSenderInterface emailSender;
    private final CodeGeneratorInterface codeGenerator;
    private final CacheServiceInterface cacheService;

    public String execute(String email) {
        Optional<User> userFound = userRepository.findByEmail(email);

        if (userFound.isEmpty())
            return null;

        User user = userFound.get();
        String code = codeGenerator.generate();
        Integer duration = 30;

        String savedCode = cacheService.put(code, user.getId().toString(), duration);

        emailSender.send(user.getEmail(), "Forgot Password", savedCode);

        return savedCode;
    }
}
