package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.IAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.ISavedAdvertisementRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class DeleteOutdatedAdsUseCase {
    private final IAdvertisementRepository advertisementRepository;
    private final ISavedAdvertisementRepository savedAdRepository;
    private final Integer DAYS_THRESHOLD = 4;

    public void execute() {
        LocalDate threshold = LocalDate.now().minusDays(DAYS_THRESHOLD);
        List<Advertisement> foundAds = advertisementRepository.findUnsavedOlderThan(threshold);

        advertisementRepository.removeAll(foundAds);
    }
}
