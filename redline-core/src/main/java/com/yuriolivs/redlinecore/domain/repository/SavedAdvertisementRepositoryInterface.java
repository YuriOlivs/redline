package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SavedAdvertisementRepositoryInterface {
    SavedAdvertisement save(SavedAdvertisement savedAd);
    Optional<SavedAdvertisement> findById(UUID id);
    List<SavedAdvertisement> findByUser(User user);
    List<SavedAdvertisement> findAllAdvertisements();
    void remove(SavedAdvertisement savedAd);
    void removeAllByUser(User user);
}
