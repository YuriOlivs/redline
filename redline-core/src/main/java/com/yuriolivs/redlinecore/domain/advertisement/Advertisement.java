package com.yuriolivs.redlinecore.domain.advertisement;

import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class Advertisement {
    @Getter
    private final UUID id;
    private String url;
    private String website;
    private String location;
    private Integer mileage;
    private final List<PriceRecord> priceHistory;
    @Setter
    private boolean active;
    private final Vehicle vehicle;
    private LocalDate lastUpdate;
    private final List<ScoreRecord> scoreHistory;
    private final LocalDateTime createdAt;

    public Advertisement(
            String url,
            String website,
            String location,
            Integer mileage,
            Vehicle vehicle
    ) {
        validateUrl(url);
        validateWebsite(website);
        validateLocation(location);
        validateMileage(mileage);
        validateVehicle(vehicle);

        this.id = UUID.randomUUID();
        this.url = url;
        this.website = website;
        this.location = location;
        this.active = true;
        this.mileage = mileage;
        this.vehicle = vehicle;
        this.lastUpdate = LocalDate.now();
        this.priceHistory = new ArrayList<>();
        this.scoreHistory = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Advertisement(
            UUID id,
            String url,
            String website,
            String location,
            Integer mileage,
            Vehicle vehicle,
            List<PriceRecord> priceHistory,
            LocalDate lastUpdate,
            List<ScoreRecord> scoreHistory,
            LocalDateTime createdAt
    ) {
        validateUrl(url);
        validateWebsite(website);
        validateLocation(location);
        validateMileage(mileage);
        validatePriceHistory(priceHistory);
        validateVehicle(vehicle);
        validateLastUpdate(lastUpdate);
        validateScoreHistory(scoreHistory);

        this.id = id;
        this.url = url;
        this.website = website;
        this.location = location;
        this.active = true;
        this.mileage = mileage;
        this.vehicle = vehicle;
        this.lastUpdate = lastUpdate;
        this.priceHistory = new ArrayList<>(priceHistory);
        this.scoreHistory = new ArrayList<>(scoreHistory);
        this.createdAt = createdAt;
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
        if (mileage == null || mileage < 0)
            throw new IllegalArgumentException("Mileage must be a positive value");
    }

    private void validateVehicle(Vehicle vehicle) {
        if (vehicle == null)
            throw new IllegalArgumentException("Vehicle can't be null");
    }

    private void validatePriceHistory(List<PriceRecord> priceHistory) {
        if (priceHistory == null || priceHistory.isEmpty())
            throw new IllegalArgumentException("Price history must contain at least one item");
    }

    private void validateScoreHistory(List<ScoreRecord> scoreHistory) {
        if (scoreHistory == null || scoreHistory.isEmpty())
            throw new IllegalArgumentException("Price history must contain at least one item");
    }

    private void validateLastUpdate(LocalDate lastUpdate) {
        if (lastUpdate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Last Update must be past or present");
    }

    private void validateCreatedAt(LocalDateTime createdAt) {
        if (createdAt.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("Created at must be past or present");
    }

    public List<PriceRecord> getPriceHistory() {
        return Collections.unmodifiableList(this.priceHistory);
    }

    public List<ScoreRecord> getScoreHistory() {
        return Collections.unmodifiableList(this.scoreHistory);
    }

    public Integer getScore() {
        return scoreHistory.get(scoreHistory.size() - 1).getValue();
    }

    public Double getPrice() {
        return priceHistory.get(priceHistory.size() - 1).getPrice();
    }

    public boolean isOutdated() {
        return lastUpdate.isBefore(LocalDate.now().minusWeeks(1));
    }

    public void registerPriceChange(Double price, LocalDate changedDate) {
        PriceRecord record = new PriceRecord(price, changedDate);
        this.priceHistory.add(record);
    }

    public void registerScoreChange(ScoreRecord scoreRecord) {
        this.scoreHistory.add(scoreRecord);
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

    public void setLastUpdate(LocalDate lastUpdate) {
        validateLastUpdate(lastUpdate);
        this.lastUpdate = lastUpdate;
    }
}
