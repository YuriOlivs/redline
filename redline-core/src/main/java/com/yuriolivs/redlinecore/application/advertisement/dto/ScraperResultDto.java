package com.yuriolivs.redlinecore.application.advertisement.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ScraperResultDto(
        Integer totalFound,
        List<ScraperAdvertisementDto> advertisementDtos,
        LocalDateTime date
) {}
