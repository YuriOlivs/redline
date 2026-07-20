package com.yuriolivs.redlinecore.domain.service;

import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperResultDto;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;

import java.util.List;

public interface IScraperClient {
    ScraperResultDto scrapeSavedAdsForUpdate(List<String> urls);
    ScraperResultDto scrapeAdsBySearchCriteria(AdvertisementSearchCriteria searchCriteria);
}
