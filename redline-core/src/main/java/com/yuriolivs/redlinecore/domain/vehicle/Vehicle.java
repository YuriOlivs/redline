package com.yuriolivs.redlinecore.domain.vehicle;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class Vehicle {
    private String brand;
    private String model;
    private String version;
    private Integer year;


    public Vehicle(
            String brand,
            String model,
            String version,
            Integer year
    ) {
        validateBrand(brand);
        validateModel(model);
        validateVersion(version);
        validateYear(year);

        this.brand = brand;
        this.model = model;
        this.version = version;
        this.year = year;
    }

    private void validateBrand(String brand) {
        if (brand == null || brand.isEmpty())
            throw new IllegalArgumentException("Brand must be valid");
    }

    private void validateModel(String model) {
        if (model == null || model.isEmpty())
            throw new IllegalArgumentException("Model must be valid");
    }

    private void validateVersion(String version) {
        if (version == null || version.isEmpty())
            throw new IllegalArgumentException("Version must be valid");
    }

    private void validateYear(Integer year) {
        int vehicleAge = LocalDate.now().getYear() - year;

        if (year > LocalDate.now().getYear())
            throw new IllegalArgumentException("Year must be valid");

        if (vehicleAge > 50)
            throw new IllegalArgumentException("Vehicle must be at max 50 years old");
    }

    public void setBrand(String brand) {
        validateBrand(brand);
        this.brand = brand;
    }

    public void setModel(String model) {
        validateModel(model);
        this.model = model;
    }

    public void setYear(Integer year) {
        validateYear(year);
        this.year = year;
    }

    public void setVersion(String version) {
        validateVersion(version);
        this.version = version;
    }
}
