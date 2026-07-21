package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISavedAdvertisementRepository {
    SavedAdvertisement save(SavedAdvertisement savedAd);
    List<SavedAdvertisement> findByUser(User user);
    List<User> findUsersByAdvertisement(String url);
    void remove(SavedAdvertisement savedAd);
    void removeAllByUser(User user);
}
