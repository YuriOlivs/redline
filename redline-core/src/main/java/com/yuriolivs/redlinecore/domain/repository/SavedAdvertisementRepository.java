package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface SavedAdvertisementRepository {
    SavedAdvertisement save(SavedAdvertisement savedAd);
    List<SavedAdvertisement> findByUser(User user);
    void remove(SavedAdvertisement savedAd);
    void removeAllByUser(User user);
}
