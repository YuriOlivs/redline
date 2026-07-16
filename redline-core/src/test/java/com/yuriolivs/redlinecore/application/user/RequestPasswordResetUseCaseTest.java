package com.yuriolivs.redlinecore.application.user;

import com.yuriolivs.redlinecore.application.user.usecase.RequestPasswordResetUseCase;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.CacheServiceInterface;
import com.yuriolivs.redlinecore.domain.service.CodeGeneratorInterface;
import com.yuriolivs.redlinecore.domain.service.EmailSenderInterface;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RequestPasswordResetUseCaseTest {
    private UserRepositoryInterface userRepository;
    private EmailSenderInterface emailSender;
    private CodeGeneratorInterface codeGenerator;
    private CacheServiceInterface cacheService;
    private RequestPasswordResetUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepositoryInterface.class);
        emailSender = Mockito.mock(EmailSenderInterface.class);
        codeGenerator = Mockito.mock(CodeGeneratorInterface.class);
        cacheService = Mockito.mock(CacheServiceInterface.class);

        useCase = new RequestPasswordResetUseCase(userRepository, emailSender, codeGenerator, cacheService);
    }

    @Test
    void assertRequestsPasswordResetSuccessfully() {
        String code = "123-456";
        String email = "yuri@email.com";

        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456",
                LocalDate.now().minusYears(20)
        );

        Mockito.when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        Mockito.when(codeGenerator.generate())
                .thenReturn(code);

        Mockito.when(cacheService.put(code, user.getId().toString(), 30))
                .thenReturn(code);

        String generatedCode = useCase.execute(email);

        assertNotNull(generatedCode);
        assertEquals(code, generatedCode);

        Mockito.verify(userRepository).findByEmail(email);
        Mockito.verify(codeGenerator).generate();
    }

    @Test
    void assertDoesNothingWhenUserDoesNotExist() {
        Mockito.when(userRepository.findByEmail("inexistente@email.com"))
                .thenReturn(Optional.empty());

        assertDoesNotThrow(() -> useCase.execute("inexistente@email.com"));

        Mockito.verify(codeGenerator, Mockito.never()).generate();
        Mockito.verify(emailSender, Mockito.never()).send(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(cacheService, Mockito.never()).put(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
