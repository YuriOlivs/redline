package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
@ToString
public class AdsScrapedEvent extends IDomainEvent {

    private final List<Advertisement> advertisements;

    public AdsScrapedEvent(List<Advertisement> advertisements) {
        validateAdvertisements(advertisements);
        this.advertisements = advertisements;
    }

    private void validateAdvertisements(List<Advertisement> advertisements) {
        if (advertisements == null || advertisements.isEmpty()) {
            throw new IllegalArgumentException("Advertisement list can't be null or empty");
        }
    }
}