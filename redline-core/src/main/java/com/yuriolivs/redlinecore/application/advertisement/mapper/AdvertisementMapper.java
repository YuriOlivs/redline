package com.yuriolivs.redlinecore.application.advertisement.mapper;

import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperAdvertisementDto;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;

import java.time.LocalDate;

public class AdvertisementMapper {
    public Advertisement toDomain(
            ScraperAdvertisementDto dto,
            ScoreRecord scoreRecord
    ) {
        Vehicle vehicle = new Vehicle(
                extractBrand(dto.brandModel()),
                String.join(" ",
                        extractModel(dto.brandModel()),
                        dto.version()
                ),
                dto.year()
        );

        Advertisement advertisement = new Advertisement(
                dto.url(),
                dto.website(),
                dto.location(),
                dto.km(),
                vehicle
        );

        advertisement.registerPriceChange(dto.price().doubleValue(), LocalDate.now());
        advertisement.registerScoreChange(scoreRecord);

        return advertisement;
    }

    private static String extractBrand(String brandModel) {
        return brandModel.split(" ")[0];
    }

    private static String extractModel(String brandModel) {
        String[] parts = brandModel.split(" ");
        return parts.length > 1 ? parts[1] : brandModel;
    }
}
