package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.IAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.ISavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class SaveAdUseCase {
    private final ISavedAdvertisementRepository savedAdRepository;
    private final IAdvertisementRepository advertisementRepository;
    private final IUserRepository userRepository;

    public SavedAdvertisement execute(
            String url,
            UUID userId
    ) {
        Optional<Advertisement> adFound = advertisementRepository.findByUrl(url);
        Optional<User> userFound = userRepository.findById(userId);

        if (adFound.isEmpty())
            throw new NotFoundException("Advertisement not found");

        if (!adFound.get().isActive())
            throw new IllegalArgumentException("Advertisement is not active");

        if (userFound.isEmpty())
            throw new NotFoundException("User not found");


        SavedAdvertisement savedAd = new SavedAdvertisement(
                adFound.get(),
                userFound.get(),
                LocalDate.now()
        );

        return savedAdRepository.save(savedAd);
    }
}
