package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;

public class DeleteUserUseCase {
    private final UserRepositoryInterface userRepository;
    private final SavedAdvertisementRepository savedAdRepository;

    public DeleteUserUseCase(
            UserRepositoryInterface userRepository,
            SavedAdvertisementRepository savedAdRepository
    ) {
        this.userRepository = userRepository;
        this.savedAdRepository = savedAdRepository;
    }

    boolean execute() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
