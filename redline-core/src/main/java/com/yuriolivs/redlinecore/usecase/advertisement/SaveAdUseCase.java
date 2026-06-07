package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


public class SaveAdUseCase {
    private final SavedAdvertisementRepositoryInterface savedAdRepository;
    private final AdvertisementRepositoryInterface advertisementRepository;
    private final UserRepositoryInterface userRepository;

    public SaveAdUseCase(
            SavedAdvertisementRepositoryInterface savedAdRepository,
            AdvertisementRepositoryInterface advertisementRepository,
            UserRepositoryInterface userRepository
    ) {
        this.savedAdRepository = savedAdRepository;
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    public SavedAdvertisement execute(
            String url,
            UUID userId
    ) {
        Optional<Advertisement> adFound = advertisementRepository.findByUrl(url);
        Optional<User> userFound = userRepository.findById(userId);

        if (adFound.isEmpty())
            throw new NotFoundException("Advertisement not found");

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
