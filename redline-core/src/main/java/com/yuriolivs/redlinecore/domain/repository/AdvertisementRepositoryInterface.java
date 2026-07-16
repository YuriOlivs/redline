package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdvertisementRepositoryInterface {
    Advertisement save(Advertisement ad);
    List<Advertisement> saveAll(List<Advertisement> ads);
    Optional<Advertisement> findByUrl(String url);
    List<Advertisement> findUnsavedOlderThan(LocalDate date);
    List<Advertisement> findBySearchCriteria(AdvertisementSearchCriteria searchCriteria);
    void remove(Advertisement ad);
    void removeAll(List<Advertisement> ads);
}
