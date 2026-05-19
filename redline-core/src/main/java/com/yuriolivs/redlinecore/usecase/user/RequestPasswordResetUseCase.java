package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.CodeGeneratorInterface;
import com.yuriolivs.redlinecore.domain.service.EmailSenderInterface;

public class RequestPasswordResetUseCase {
    private final UserRepositoryInterface userRepository;
    private final EmailSenderInterface emailSender;
    private final CodeGeneratorInterface codeGenerator;

    public RequestPasswordResetUseCase(
            UserRepositoryInterface userRepository,
            EmailSenderInterface emailSender,
            CodeGeneratorInterface codeGenerator
    ) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
        this.codeGenerator = codeGenerator;
    }

    void execute(String email) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
