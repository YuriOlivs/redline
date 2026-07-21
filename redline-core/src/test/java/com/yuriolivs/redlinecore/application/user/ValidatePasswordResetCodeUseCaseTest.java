package com.yuriolivs.redlinecore.application.user;

import com.yuriolivs.redlinecore.application.user.usecase.ValidatePasswordResetCodeUseCase;
import com.yuriolivs.redlinecore.domain.service.ICacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatePasswordResetCodeUseCaseTest {
    private ICacheService cacheService;
    private ValidatePasswordResetCodeUseCase useCase;

    @BeforeEach
    void setUp() {
        cacheService = Mockito.mock(ICacheService.class);
        useCase = new ValidatePasswordResetCodeUseCase(cacheService);
    }

    @Test
    void assertReturnsTrueWithValidCode() {
        String code = "123-456";

        Mockito.when(cacheService.get(code))
                .thenReturn(UUID.randomUUID().toString());

        UUID isValid = useCase.execute(code);

        assertNotNull(isValid);

        Mockito.verify(cacheService).get(code);
    }

    @Test
    void assertReturnsFalseWithInvalidCode() {
        String code = "123-456";

        Mockito.when(cacheService.get(code))
                .thenReturn(null);

        UUID isValid = useCase.execute(code);

        assertNull(isValid);

        Mockito.verify(cacheService).get(code);
    }
}
