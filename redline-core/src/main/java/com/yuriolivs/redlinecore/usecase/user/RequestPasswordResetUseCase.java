package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.CacheServiceInterface;
import com.yuriolivs.redlinecore.domain.service.CodeGeneratorInterface;
import com.yuriolivs.redlinecore.domain.service.EmailSenderInterface;

public class RequestPasswordResetUseCase {
    private final UserRepositoryInterface userRepository;
    private final EmailSenderInterface emailSender;
    private final CodeGeneratorInterface codeGenerator;
    private final CacheServiceInterface cacheService;

    public RequestPasswordResetUseCase(
            UserRepositoryInterface userRepository,
            EmailSenderInterface emailSender,
            CodeGeneratorInterface codeGenerator,
            CacheServiceInterface cacheService
    ) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
        this.codeGenerator = codeGenerator;
        this.cacheService = cacheService;
    }

    String execute(String email) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
