package com.yuriolivs.redlinecore.domain.service;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;

import java.util.List;

public interface ScraperClientInterface {
    List<Advertisement> findSavedAdsForUpdate(List<Advertisement> savedAds);
    List<Advertisement> findAdsBySearchCriteria(AdvertisementSearchCriteria searchCriteria);
}
