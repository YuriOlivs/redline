package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface SavedAdvertisementRepository {
    SavedAdvertisement save(SavedAdvertisement savedAd);
    Optional<List<SavedAdvertisement>> findByUser(User user);
    SavedAdvertisement remove(SavedAdvertisement savedAd);
}
