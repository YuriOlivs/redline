package com.yuriolivs.redlinecore.domain.advertisement;

import com.yuriolivs.redlinecore.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class SavedAdvertisement {
    private UUID id;
    private Advertisement advertisement;
    private User user;
    private LocalDate savedDate;

    public SavedAdvertisement(
            UUID id,
            Advertisement advertisement,
            User user,
            LocalDate savedDate
    ) {
        validateId(id);
        validateSavedDate(savedDate);
        validateAdvertisement(advertisement);
        validateUser(user);

        this.id = id;
        this.advertisement = advertisement;
        this.user = user;
        this.savedDate = savedDate;
    }

    public SavedAdvertisement(
            Advertisement advertisement,
            User user,
            LocalDate savedDate
    ) {
        validateSavedDate(savedDate);
        validateAdvertisement(advertisement);
        validateUser(user);

        this.id = UUID.randomUUID();
        this.advertisement = advertisement;
        this.user = user;
        this.savedDate = savedDate;
    }

    private void validateId(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("ID can't be null");
    }

    private void validateSavedDate(LocalDate savedDate) {
        if (savedDate == null || savedDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Saved date must be past or present");
    }

    private void validateAdvertisement(Advertisement advertisement) {
        if (advertisement == null)
            throw new IllegalArgumentException("Advertisement can't be null");
    }

    private void validateUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");
    }

    public void setId(UUID id) {
        validateId(id);
        this.id = id;
    }

    public void setSavedDate(LocalDate savedDate) {
        validateSavedDate(savedDate);
        this.savedDate = savedDate;
    }

    public void setAdvertisement(Advertisement advertisement) {
        validateAdvertisement(advertisement);
        this.advertisement = advertisement;
    }

    public void setUser(User user) {
        validateUser(user);
        this.user = user;
    }
}
