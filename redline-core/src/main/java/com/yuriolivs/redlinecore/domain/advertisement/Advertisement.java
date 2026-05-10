package com.yuriolivs.redlinecore.domain.advertisement;

import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter
public class Advertisement {
    private String url;
    private String website;
    private String location;
    private Integer mileage;
    private List<PriceRecord> priceHistory;
    @Setter
    private boolean active;
    @Setter
    private Vehicle vehicle;

    public Advertisement(
        String url,
        String website,
        String location,
        boolean active,
        Integer mileage,
        Vehicle vehicle,
        List<PriceRecord> priceHistory
    ) {
        validateUrl(url);
        validateWebsite(website);
        validateLocation(location);
        validateMileage(mileage);
        validatePriceHistory(priceHistory);
        validateVehicle(vehicle);

        this.url = url;
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

    private void validateVehicle(Vehicle vehicle) {
        if (vehicle == null)
            throw new IllegalArgumentException("Vehicle can't be null");
    }

    private void validatePriceHistory(List<PriceRecord> priceHistory) {
        if (priceHistory == null || priceHistory.isEmpty())
            throw new IllegalArgumentException("Price history must contain at least one item");
    }

    public List<PriceRecord> getPriceHistory() {
        return Collections.unmodifiableList(this.priceHistory);
    }

    public void registerPriceChange(Double price, LocalDate changedDate) {
        PriceRecord record = new PriceRecord(price, changedDate);
        this.priceHistory.add(record);
    }

    public void setUrl(String url) {
        validateUrl(url);
        this.url = url;
    }

    public void setWebsite(String website) {
        validateWebsite(website);
        this.website = website;
    }

    public void setLocation(String location) {
        validateLocation(location);
        this.location = location;
    }

    public void setMileage(Integer mileage) {
        validateMileage(mileage);
        this.mileage = mileage;
    }
}
