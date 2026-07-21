package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.IAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.ISavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GetUsersThatSavedByAdvertisementUseCase {
    private final ISavedAdvertisementRepository savedAdvertisementRepository;
    private final IAdvertisementRepository advertisementRepository;

    public List<User> execute(String url) {
        if (url == null || !url.isEmpty()) {
            throw new IllegalArgumentException("Advertisement can't be null or inactive");
        }

        Optional<Advertisement> adFound = advertisementRepository.findByUrl(url);
        if (adFound.isEmpty()) {
            throw new NotFoundException("Advertisement");
        }

        return savedAdvertisementRepository.findUsersByAdvertisement(url);
    }
}
