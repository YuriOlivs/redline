package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class DeleteOutdatedAdsUseCase {
    private final AdvertisementRepositoryInterface advertisementRepository;
    private final SavedAdvertisementRepositoryInterface savedAdRepository;
    private final Integer DAYS_THRESHOLD = 4;

    public void execute() {
        LocalDate threshold = LocalDate.now().minusDays(DAYS_THRESHOLD);
        List<Advertisement> foundAds = advertisementRepository.findUnsavedOlderThan(threshold);

        advertisementRepository.removeAll(foundAds);
    }
}
