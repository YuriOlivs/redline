package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdvertisementRepositoryInterface {
    Advertisement save(Advertisement ad);
    Optional<Advertisement> findByUrl(String url);
    List<Advertisement> findByWebsite(String website);
    List<Advertisement> findByLocation(String location);
    List<Advertisement> findByActive(boolean active);
    List<Advertisement> findByMileage(Integer mileage);
    List<Advertisement> findByVehicleBrand(String brand);
    List<Advertisement> findByVehicleYear(String year);
    List<Advertisement> findByVehicleBrandAndVehicleModel(String brand, String model);
    List<Advertisement> findByVehicleBrandAndVehicleYear(String brand, String year);
    List<Advertisement> findByVehicleBrandAndVehicleModelAndVehicleYear(String brand, String model, String year);
    List<Advertisement> findUnsavedOlderThan(LocalDate date, List<String> savedUrls);
    void remove(Advertisement ad);
}
