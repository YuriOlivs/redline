package com.yuriolivs.redlinecore.application.advertisement.dto;

public record ScraperAdvertisementDto (
        String brandModel,
        String version,
        Integer year,
        String website,
        Float price,
        Integer km,
        String location,
        String url
) {}
