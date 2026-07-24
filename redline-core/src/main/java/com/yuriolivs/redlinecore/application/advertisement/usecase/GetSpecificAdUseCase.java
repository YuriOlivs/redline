package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.IAdvertisementRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class GetSpecificAdUseCase {
    private final IAdvertisementRepository advertisementRepository;

    public Advertisement execute(UUID id) {
        Optional<Advertisement> adFound = advertisementRepository.findById(id);
        if (adFound.isEmpty())
            throw new NotFoundException("Advertisement");

        return adFound.get();
    }

    public Advertisement execute(String url) {
        Optional<Advertisement> adFound = advertisementRepository.findByUrl(url);
        if (adFound.isEmpty())
            throw new NotFoundException("Advertisement");

        return adFound.get();
    }
}
