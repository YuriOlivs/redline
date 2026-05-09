package com.yuriolivs.redlinecore.domain.advertisement;

import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class Advertisement {
    private String url;
    private LocalDate followedDate;
    private String website;
    private String location;
    private boolean active;
    private Integer mileage;
    private Vehicle vehicle;
    private List<PriceRecord> priceHistory;

    public Advertisement(
        String url,
        LocalDate followedDate,
        String website,
        String location,
        boolean active,
        Integer mileage,
        Vehicle vehicle,
        List<PriceRecord> priceHistory
    ) {
        validateUrl(url);
        validateFollowedDate(followedDate);
        validateWebsite(website);
        validateLocation(location);
        validateMileage(mileage);
        validatePriceHistory(priceHistory);

        this.url = url;
        this.followedDate = followedDate;
        this.website = website;
        this.location = location;
        this.active = active;
        this.mileage = mileage;
        this.vehicle = vehicle;
    }

    private void validateUrl(String url) {
        if (url == null || url.isEmpty())
            throw new IllegalArgumentException("URL must be valid.");
    }

    private void validateFollowedDate(LocalDate followedDate) {
        if (followedDate != null && followedDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Followed date must be future or present.");
    }

    private void validateWebsite(String website) {
        if (website == null || website.isEmpty())
            throw new IllegalArgumentException("Website must be valid");
    }

    private void validateLocation(String location) {
        if (location == null || location.isEmpty())
            throw new IllegalArgumentException("Location must be valid");
    }

    private void validateMileage(Integer mileage) {
        if (mileage == null || mileage <= 0)
            throw new IllegalArgumentException("Mileage must be positive and greater than 0");
    }

    private void validatePriceHistory(List<PriceRecord> priceHistory) {
        if (priceHistory == null || priceHistory.isEmpty())
            throw new IllegalArgumentException("Price history must contain at least one item");
    }
}
