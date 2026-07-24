package com.yuriolivs.redlinecore.application.user.usecase;

import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.service.Email;
import com.yuriolivs.redlinecore.domain.service.ICacheService;
import com.yuriolivs.redlinecore.domain.service.ICodeGenerator;
import com.yuriolivs.redlinecore.domain.service.IEmailSender;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RequestPasswordResetUseCase {
    private final IUserRepository userRepository;
    private final IEmailSender emailSender;
    private final ICodeGenerator codeGenerator;
    private final ICacheService cacheService;

    public String execute(String email) throws Exception {
        Optional<User> userFound = userRepository.findByEmail(email);

        if (userFound.isEmpty())
            return null;

        User user = userFound.get();
        String code = codeGenerator.generate();
        Integer duration = 30;

        String savedCode = cacheService.put(code, user.getId().toString(), duration);

        emailSender.send(new Email(user.getEmail(), "Forgot Password", savedCode));

        return savedCode;
    }
}
