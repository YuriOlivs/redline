package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.CacheServiceInterface;
import com.yuriolivs.redlinecore.domain.service.CodeGeneratorInterface;
import com.yuriolivs.redlinecore.domain.service.EmailSenderInterface;

public class ForgotUserPasswordUseCase {
    private final UserRepositoryInterface userRepository;
    private final EmailSenderInterface emailSender;
    private final CodeGeneratorInterface codeGenerator;
    private final CacheServiceInterface cacheService;

    public ForgotUserPasswordUseCase(
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

    boolean execute() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
