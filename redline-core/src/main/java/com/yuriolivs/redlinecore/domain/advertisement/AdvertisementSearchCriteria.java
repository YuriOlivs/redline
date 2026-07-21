package com.yuriolivs.redlinecore.domain.advertisement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Builder
public class AdvertisementSearchCriteria {
    private String brand;
    private String model;
    private Integer year;
    private String website;
    private Integer mileage;
    private Integer page;
    private Integer size;

    public AdvertisementSearchCriteria(
            String brand,
            String model,
            Integer year,
            String website,
            Integer mileage,
            Integer page,
            Integer size
    ) {
        validateMinimumCriteria(brand, model);
        validateYear(year);
        validateMileage(mileage);

        this.brand = brand;
        this.model = model;
        this.year = year;
        this.website = website;
        this.mileage = mileage;
        this.page = page;
        this.size = size;
    }

    private void validateMinimumCriteria(String brand, String model) {
        if (brand == null || brand.isEmpty() && model == null || model.isEmpty())
            throw new IllegalArgumentException("At least brand or model must be filled");
    }

    private void validateYear(Integer year) {
        if (year < 0)
            throw new IllegalArgumentException("Year must be a positive value");
    }

    private void validateMileage(Integer mileage) {
        if (mileage < 0)
            throw new IllegalArgumentException("Mileage must be a positive value");
    }
}
