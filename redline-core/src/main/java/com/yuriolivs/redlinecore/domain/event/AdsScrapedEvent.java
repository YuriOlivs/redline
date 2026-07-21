package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;

import java.util.List;

public record AdsScrapedEvent(
        List<Advertisement> advertisements
) {

    public AdsScrapedEvent{
        validateAdvertisements(advertisements);
    }

    private void validateAdvertisements(List<Advertisement> advertisement) {
        if (advertisement == null || advertisement.isEmpty())
            throw new IllegalArgumentException("Advertisement list can't be null or empty");
    }
}
