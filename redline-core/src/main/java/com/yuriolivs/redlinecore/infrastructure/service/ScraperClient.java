package com.yuriolivs.redlinecore.infrastructure.service;

import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperResultDto;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;
import com.yuriolivs.redlinecore.domain.service.IScraperClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScraperClient implements IScraperClient {
    @Override
    public ScraperResultDto scrapeSavedAdsForUpdate(List<String> urls) {
        return null;
    }

    @Override
    public ScraperResultDto scrapeAdsBySearchCriteria(AdvertisementSearchCriteria searchCriteria) {
        return null;
    }
}
